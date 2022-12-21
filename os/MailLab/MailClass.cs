using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Text;
using System.Threading.Tasks;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.Tab;
using System.Xml.Linq;

namespace MailLab
{
    internal class MailClass
    {//"pomaprokhorovich583@gmail.com""iloveyouvika2.1.2021"
        private  string Host = "smtp.yandex.com";
        private  string DisplayName = "User";
        private  string myAddres = "romam185@yandex.ru";
        private  string pass ="lsolbijyikzycnmk" ;
        private  int port=25 ;

        private string userAddres;
        private string text;
        private string[] files;

       public void SendMail()
        {
            MailAddress from = new MailAddress(myAddres, DisplayName);
            MailAddress to = new MailAddress(userAddres);
            MailMessage m = new MailMessage(from, to);
            m.Subject = text;
            foreach (var item in files)
            {
                m.Attachments.Add(new Attachment(item));
            }
            SmtpClient smtp = new SmtpClient(Host, port);
            smtp.UseDefaultCredentials = false;
            smtp.Credentials = new NetworkCredential(myAddres, pass);
            smtp.EnableSsl = true;
            smtp.Send(m);
        }
        public MailClass(string userAddres, string text, string[] files,string host, string add, string pas,int p)
        {
            this.userAddres = userAddres;
            this.text = text;
            this.files = files;
            Host = host;
            myAddres = add;
            pass = pas;
            port= p;
        }
    }

    
}
