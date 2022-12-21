using MIVS_1;
using System;
using System.Windows.Forms;

namespace NewMivs1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            dataGridView1.RowCount = 15;
            dataGridView1.ColumnCount = 4;

           
        }
        TriangleSV sv = new TriangleSV();

        private void button1_Click(object sender, EventArgs e)
        {
            for (int i = 0; i < 15; i++)
            {
                dataGridView1[0, i].Value = "[" + Math.Round(sv.n[i], 2) + "; " + Math.Round(sv.n[i + 1], 2) + "]";
            }

            for (int i = 0; i < 15; i++)
            {
                dataGridView1[1, i].Value = Math.Round(sv.Mids[i], 2);
            }
            for (int i = 0; i < 15; i++)
            {
                dataGridView1[2, i].Value = sv.countIntervals[i];
            }
            for (int i = 0; i < 15; i++)
            {
                dataGridView1[3, i].Value = Math.Round(sv.Heights[i], 2);
            }
            double[] mas = sv.Mids;
            for (int i = 0; i < 15; i++)
            {
                mas[i] = Math.Round(sv.Mids[i], 2);
            }
            chart1.Series[0].Points.DataBindXY(mas, sv.Heights);
            chart1.Series.Add("f(x)");
            chart1.Series[1].ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Spline;
            double y = 0;
            for (int i = 0; i < 15; i++)
            {
                if (sv.Mids[i] >= -sv.a && sv.Mids[i] <= 0)
                    y = (sv.a + sv.Mids[i]) * 2 / (sv.a * (sv.a + sv.b));
                else if (sv.Mids[i] >= 0 && sv.Mids[i] <= sv.b)
                    y = (sv.b - sv.Mids[i]) * 2 / (sv.b * (sv.a + sv.b));
                chart1.Series[1].Points.AddXY(sv.Mids[i], y);
            }
            chart1.ChartAreas[0].Area3DStyle.Enable3D = false;
            double ch = sv.xee;
            if (ch < 23.7)
            {
                label1.Text = "Гипотеза верна. хи квадрат:" + ch;
            }
            else
            {
                label1.Text = "Гипотеза не верна. хи квадрат:"+ sv.xee;
            }

        }
    }
}

