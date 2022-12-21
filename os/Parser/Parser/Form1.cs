using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Parser
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (int.TryParse(textBox2.Text,out int count))
            {

                
                fileLinks.Items.Clear();
                insLinks.Items.Clear();
                HtmlParser parser = new HtmlParser(count, textBox1.Text);
                parser.Parse();
                var inside = parser.GetInsideLinks();
                var files = parser.GetFileLinks();
                var len = parser.GetLen();
               

                foreach (var item in inside)
                {
                    insLinks.Items.Add(item);
                }

                foreach (var item in files)
                {
                    fileLinks.Items.Add(item);
                }
                MessageBox.Show("Суммарный вес всех файлов: " + len + " байт. Количество файлов:"+files.Count + " колво ссылок:"+parser.GetInsideLinks().Count);
            }
            else
            {
                MessageBox.Show("введите целое число в поле количества ссылок!");
            }
        }
    }
}
