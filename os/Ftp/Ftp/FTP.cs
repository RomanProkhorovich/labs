using Microsoft.VisualBasic;
using System;
using System.Collections.Generic;
using System.Drawing.Text;
using System.Linq;
using System.Net;
using System.Runtime.ConstrainedExecution;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace Ftp
{
    internal class FTP
    {
        private enum fileTypes
        {
            txt,
            img,
            other
        }
        private string login;
        private string password;
        private string host;


        private List<string> files;
        private List<string> imgs;
        private List<string> txts;
        private List<string> other;

        private float size = 0;
        private float imgSize = 0;
        private float textSize = 0;
        private float otherSize = 0;

        public List<string> Files { get => files; set => files = value; }
        public List<string> Imgs { get => imgs; set => imgs = value; }
        public List<string> Txts { get => txts; set => txts = value; }
        public List<string> Other { get => other; set => other = value; }
        public float Size { get => size; set => size = value; }
        public float ImgSize { get => imgSize; set => imgSize = value; }
        public float TextSize { get => textSize; set => textSize = value; }
        public float OtherSize { get => otherSize; set => otherSize = value; }

        public TreeNode dir_nodes;
        

        public FTP(string login, string password, string host)
        {
            this.login = login;
            this.password = password;
            this.host = "ftp://"+host;
            Files=new List<string>();
            Txts = new();
            Imgs = new();
            Other = new();
        }

        public void GetFiles()
        {

            dir_nodes = new TreeNode(host);
            dir_nodes.Name = host;
            size = 0;
            Regex regex = new Regex(@"^([d-])([rwxt-]{3}){3}\s+\d{1,}\s+.*?(\d{1,})\s+(\w+\s+\d{1,2}\s+(?:\d{4})?)(\d{1,2}:\d{2})?\s+(.+?)\s?$",
                RegexOptions.Compiled | RegexOptions.Multiline | RegexOptions.IgnoreCase | RegexOptions.IgnorePatternWhitespace);

            List<string> dirs = new List<string>();

            dirs.Add(host);
            int i = 0;
            var currentRoot = dir_nodes;
            while (dirs.Count != 0)
            {

                string current = dirs[0];
                FtpWebRequest request = (FtpWebRequest)WebRequest.Create(current);
                request.Credentials = new NetworkCredential(login, password);
                request.Method = WebRequestMethods.Ftp.ListDirectoryDetails;
                FtpWebResponse response = (FtpWebResponse)request.GetResponse();
                StreamReader reader = new StreamReader(response.GetResponseStream());
                string res = (reader.ReadToEnd());
                string[] FileMas = res.Split("\n");
                foreach (string file in FileMas)
                {
                    var a = regex.Match(file);
                    if (a.Success)
                    {
                        var currNode = new TreeNode();
                        if (a.Groups[1].Value == "d")
                        {
                            dirs.Add(current + "/" + a.Groups[6].Value);
                        }
                        else
                        {
                            Files.Add(a.Groups[6].Value);
                            size += float.Parse(a.Groups[3].Value);
                            switch (GetFileType(a.Groups[6].Value))
                            {
                                case fileTypes.txt:
                                    addFile(Txts, a.Groups[6].Value, float.Parse(a.Groups[3].Value), ref textSize);
                                    break;
                                case fileTypes.img:
                                    addFile(Imgs, a.Groups[6].Value, float.Parse(a.Groups[3].Value), ref imgSize);
                                    break;
                                case fileTypes.other:
                                    addFile(Other, a.Groups[6].Value, float.Parse(a.Groups[3].Value), ref otherSize);
                                    break;

                            }
                        }

                        currentRoot.Nodes.Add(current + "/" + a.Groups[6].Value, a.Groups[6].Value);
                    }

                }
                dirs.Remove(current);
                if (dirs.Count != 0)
                    currentRoot = findNode(dir_nodes, dirs[0]);
            }
        }

        TreeNode findNode (TreeNode n, String value)
        {
            if (n == null) return n;
            if (n.Name == value) return n;
            foreach (TreeNode subNode in n.Nodes)
            {
                TreeNode foundNode = findNode(subNode, value);
                if (foundNode != null) return foundNode;
            }
            return null;
        }

        private void addFile(List<string> list, string fileName,float fileSize,ref float someSize)
        {
            list.Add(fileName);
            someSize += fileSize;
                
        }

        private fileTypes GetFileType(string name)
        {
            string[] txtsf = {"pdf","doc","docx","xml"};
            string[] imgsf = {"png","jpg","jpeg","svg" };
            var m = Regex.Match(name, @"[/\w\s%""''\\[\]{}()+-]*\.([\w]+)$");
            if (!m.Success)
            {
                throw new ArgumentException();
            }
            

            var type = m.Groups[1].Value;
            if (txtsf.Contains(type))
                return fileTypes.txt;
            
            if (imgsf.Contains(type))
                return fileTypes.img;
            return fileTypes.other;

        }

        

    }

   
}
