using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MailLab
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            //mail.SendMail();
            
        }

        private void button1_Click(object sender, EventArgs e)
        {

            String[] mas = textBox3.Text.Split('\n');

            try{
                MailClass mail = new MailClass(textBox2.Text, textBox1.Text, mas,smtp.Text,log.Text,pas.Text,int.Parse(por.Text));
                mail.SendMail();
            }
            catch(Exception){
                MessageBox.Show("что то пошло не так! проверьте поля!");
            }
           
        }
    }
}
