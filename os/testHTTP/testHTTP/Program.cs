

using System.Net.Http.Headers;

using (HttpClient client = new HttpClient())
{

    HttpResponseMessage message =  client.GetAsync("http://avto-m.com").Result;
    //HttpResponseMessage message = await client.GetAsync("https://www.rulit.me/download-books-386934.html?t=pdf");

    var smt = message.Headers;

    //var len = message.Content.Headers.ContentLength;
    //message.EnsureSuccessStatusCode();
    //if (message.Content.Headers.ContentType != null && message.Content.Headers.ContentType.ToString()=="application/pdf")
    //{

    //    Console.WriteLine(len);
    //}

    
    //string resp = message.Content.ReadAsStringAsync().Result;
    //List<string> res=GetTags(resp);
    //res = GetLinks(res.ToArray());
    //foreach (var link in smt)
    //{
    //    //for (int i = 0; i < link.Value.ToList().Count; i++)
    //    //{
    //    //    Console.Write(link.Key+" " + link.Value.ToList()[i]);
    //    //    Console.WriteLine();
    //    ////}
    //    //Console.WriteLine(link.Key);
    //    //Console.WriteLine();
    //    //foreach (var item in link.Value)
    //    //{
    //    //    Console.WriteLine(item);
    //    //}
    //}
    //Console.WriteLine(IsInsideLink("http://www.shpl.ru/readers/"));


    //foreach (var item in res)
    //{
    //    Console.WriteLine(item);
    //}
    Console.WriteLine(IsFile("http://hpc.ssau.ru/files/doc/ssc_regnote.pdff"));
}



 bool IsFile(string address)
{
    if (address[address.Length - 4] == '.' && (address[address.Length - 1] == 'f' && address[address.Length - 2] == 'd' && address[address.Length - 3] == 'p')
        || (address[address.Length - 4] == '.' && address[address.Length - 1] == 'c' && address[address.Length - 2] == 'o' && address[address.Length - 3] == 'd')
        )
    {
        return true;
    }
    else if (address[address.Length - 1] == 'x'
        && address[address.Length - 2] == 'c'
        && address[address.Length - 3] == 'o'
        && address[address.Length - 4] == 'd' && address[address.Length - 5] == '.')
    {
        return true;
    }
    //var type = message.Content.Headers.ContentType;
    //if (type==null)
    //{
    //    return false;
    //}
    //foreach (var item in fileTypes)
    //{
    //    if (type.ToString()==item)
    //    {

    //        len += message.Content.Headers.ContentLength;
    //        return true;
    //    }
    // }
    return false;
}

bool IsInsideLink(string link)
{
    if (link.Contains("http://www.shpl.ru")) return true;
    return false;
}
List<string> GetTags(string html)
{
    html = html.Replace(" ", "");
    List<string> res = new List<string>();
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
}

 List<string> GetLinks(string[] tags)
{
    List<string> res = new List<string>();
    foreach (string tag in tags)
    {
        tag.Replace(" ", "");
        if (tag.Contains("ahref="))
        {
            int pos = 7;
            string link = "";
            string current = tag[pos].ToString();
            while (current != ("\""))
            {
                link += current;
                pos++;
                current = tag[pos].ToString();
            }
            if (link.Contains("http://avto-m.com"))
            {
                res.Add(link.Trim());
            }
            else if (!link.Contains("http") && !link.Contains("https")) res.Add("http://avto-m.com" + link.Trim());
            

        }
    }
    return res;
}
