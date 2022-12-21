namespace MIVS_1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            dataGridView1.RowCount = 15;
            dataGridView1.ColumnCount = 4;

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
        }
         TriangleSV sv =  new TriangleSV();
        private void Fill()
        {

        }

    }
}