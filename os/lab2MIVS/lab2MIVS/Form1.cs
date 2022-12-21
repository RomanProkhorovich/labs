using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace lab2MIVS
{
    public partial class Form1 : Form
    {
        
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Potok p=new Potok(double.Parse(textBox1.Text),double.Parse(textBox2.Text));
            double[] res = p.GetExp();
            double[] resres = p.GetTime(res);
            for (int i = 0; i < resres.Length; i++)
            {
                listBox1.Items.Add(resres[i]);
                if (i>0)
                {
                    listBox2.Items.Add((resres[i] - resres[i - 1]).ToString());
                }
                
            }
            label1.Text = p.t.ToString();
        }
    }
}
