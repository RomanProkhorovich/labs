namespace Ftp
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            this.WindowState = FormWindowState.Maximized;
           
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var a = new FTP(login.Text, password.Text, host.Text);
            a.GetFiles();

            var txts = a.Txts;
            var imgs = a.Imgs;
            var other = a.Other;
            Print(a.Files, a.Size, listBox1, label1);
            Print(a.Txts, a.TextSize, listBox2, label2);
            Print(a.Imgs, a.ImgSize, listBox3, label3);
            Print(a.Other, a.OtherSize, listBox4, label4);
            treeView1.Nodes.Add(a.dir_nodes);
        }

        private void Print(List<string> a,float size,ListBox listBox,Label label)
        {
            foreach (var file in a)
            {
                listBox.Items.Add(file);
            }
            label.Text = size.ToString();
        }
    }
}