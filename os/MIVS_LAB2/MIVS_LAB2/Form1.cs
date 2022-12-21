using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MIVS_LAB2
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            var a = new Potok(double.Parse(timeBox.Text), double.Parse(alphaBox.Text), double.Parse(lambdaBox.Text));
            var b = a.Start(out int count);
            foreach (var item in b)
            {
                listBox1.Items.Add(item.ToString().Substring(0, 7));
            }
            foreach (var item in a.GetTimesDif())
            {
                listBox2.Items.Add(item.ToString().Substring(0,7));
            }
            MessageBox.Show("Количчество событий:" + count);
        }
    }
}
