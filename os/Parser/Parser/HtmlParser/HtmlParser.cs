using System;
using System.Collections.Generic;
using System.Drawing;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Runtime.CompilerServices;
using System.Runtime.Remoting.Messaging;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using static System.Windows.Forms.LinkLabel;

namespace Parser
{
    internal class HtmlParser
    {
        //    private List<string> tags = new List<string>();
        private List<string> insideLinks = new List<string>();
        private List<string> fileLinks = new List<string>();
        private List<string> outsideLinks = new List<string>();

        private int MaxLinksCount;
        private List<string> files = new List<string>();
        private readonly string BaseUrl;
        //private string[] fileTypes = { "application/pdf", "application/doc", "application/docx" };
        private long len;

        public List<string> GetInsideLinks()
        {
            return insideLinks;
        }

        public List<string> GetOutsideLinks()
        {
            return outsideLinks;
        }

        public List<string> GetFileLinks()
        {
            return fileLinks;
        }
        public long GetLen()
        {
            return len;
        }
        public HtmlParser(int max, string link)
        {
            MaxLinksCount = max;
            BaseUrl = link;
            len = 0;
        }
        public void Parse()
        {
            var tasks_response = new List<Task<HttpResponseMessage>>();
            List<string> res = new List<string>();
            using (HttpClient client = new HttpClient())
            {

                insideLinks.Add(BaseUrl);
                this.client = client;
                ParseLink(client);

            }

        }
        HttpClient client;
        private async void ParseLink(HttpClient client)
        {
            int links_index = 0;
            while(insideLinks.Count < MaxLinksCount && links_index < insideLinks.Count)
            {

                string resp_raw;
                try
                {
                    var tt = client.GetAsync(insideLinks[links_index]);
                    if (tt.Result.StatusCode != HttpStatusCode.OK)
                        throw new HttpRequestException();
                    resp_raw = tt.Result.Content.ReadAsStringAsync().Result; ;
                    
                }
                catch (HttpRequestException)
                {
                    links_index++;
                    continue;
                }
                catch (ArgumentNullException)
                {
                    links_index++;
                    continue;
                }
                
                var tags = GetTags(resp_raw);
                var links = GetLinks(tags);
                for (int i = 0; i < links.Count; i++)
                {
                    if (IsFile(links[i]) && isUnqueLink(links[i], fileLinks))
                    {
                        if (isntPicture(links[i]))
                        {
                            fileLinks.Add(links[i]);
                            len+= (long)client.GetAsync(links[i]).Result.Content.Headers.ContentLength;
                        }
                        insideLinks.Remove(links[i]);
                        continue;
                    }
                    else if (!IsInsideLink(links[i]))
                        continue;
                    else if (isUnqueLink(links[i], insideLinks) && !IsFile(links[i]) && IsInsideLink(links[i]))
                    {
                        insideLinks.Add(links[i]);
                    }
                }
                links_index++;
            }
        }

        private bool isUnqueLink(string link, List<String> mas)
        {
            return !mas.Contains(link);
        }

        private bool isntPicture(string address)
        {
            var m = Regex.Match(address, @"[/\w\s%""''\\[\]{}()+-]*\.([.\w]+)$");
            if (m.Success)
                return m.Groups[1].Value != "png"
                    && m.Groups[1].Value != "jpg"
                    && m.Groups[1].Value != "jpeg"
                    && m.Groups[1].Value != "webm"
                    && m.Groups[1].Value != "svg"
                    && m.Groups[1].Value != "fraq";
            return false;
        }

        public bool IsFile(string address)
        {
            var m = Regex.Match(address, @"[/\w\s%""''\\[\]{}()+-]*\.([.\w]+)$");
            if (m.Success)
                return m.Groups[1].Value != "html";
            return false;
        }

     
        private List<string> GetLinks(List<string> tags)
        {
            var ret = new List<string>();
            foreach (var div in tags)
            {
                Match m = Regex.Match(div, @"<a[\s:;/.a-zA-Z0-9?""=-]*href=[""']([:/.a-zA-Z0-9?=_-]+)[""'][\s\t\w<>""'':/=?+-]*>");
                if (!m.Success)
                    continue;
                
                var extracted = m.Groups[1].Value;
                
                if (extracted.Contains(BaseUrl) || extracted[0] == '/' || extracted[0] == '?' || !extracted.Contains("/"))
                {
                    if (extracted[0] == '?' || extracted.Contains("/"))
                        extracted =  BaseUrl + '/' + extracted.Trim(); ;
                   

                    if (ret.Contains(extracted))
                        continue;
                    ret.Add(extracted);
                    continue;
                }
            }
            return ret;
            /*
            List<string> res = new List<string>();
            foreach (string tag in tags)
            {
                var m = Regex.Match(tag, @"<\s*a.*href=""([/a-zA-Z0-9]*)""\s*[^>]*>");
                if (!m.Success)
                    continue;
                string link = m.Groups[1].Value;
                link = GetFullLink(link);
                if (isUnqueLink(link, insideLinks))
                {
                    res.Add(link);

                }
            }
            return res;
            */
        }
        private bool IsInsideLink(string link)
        {
            if (link.Contains(BaseUrl) && link != BaseUrl && link != BaseUrl + "#" && link != BaseUrl + "/") return true;
            return false;
        }
        private string GetFullLink(string link)
        {

            if (link.Contains("http") || link.Contains("https"))
            {
                return link.Trim();
            }
            if (link[0] != '/')
            {
                return BaseUrl + '/' + link.Trim();
            }
            return BaseUrl + link.Trim();
        }
        private List<string> GetTags(string html)
        {

            var doc_prep = html.Replace("\n", "").Replace("\r", "").Replace("\t", " ");
            var doc = Regex.Replace(doc_prep, @"\s\s+", " ").Replace("><", ">\n<").Replace("> <", ">\n<").Split('\n');
            var ret = new List<string>();
            ret.AddRange(doc);
            return ret;
            /*
            List<string> res = new List<string>();
            html = html.Replace(" ", "");
            string tag = "";
            bool isTag = false;
            for (int i = 0; i < html.Length; i++)
            {

                if (html[i] == '<')
                {
                    isTag = true;
                }
                else if (html[i] == '>')
                {
                    res.Add(tag);
                    tag = "";
                    isTag = false;
                }
                else if (isTag)
                {
                    tag += html[i];
                }
            }
            return res;
            */
        }
    }
}
