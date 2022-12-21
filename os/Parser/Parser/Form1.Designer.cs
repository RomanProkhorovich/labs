namespace Parser
{
    partial class Form1
    {
        /// <summary>
        /// Обязательная переменная конструктора.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Освободить все используемые ресурсы.
        /// </summary>
        /// <param name="disposing">истинно, если управляемый ресурс должен быть удален; иначе ложно.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Код, автоматически созданный конструктором форм Windows

        /// <summary>
        /// Требуемый метод для поддержки конструктора — не изменяйте 
        /// содержимое этого метода с помощью редактора кода.
        /// </summary>
        private void InitializeComponent()
        {
            this.insLinks = new System.Windows.Forms.ListBox();
            this.fileLinks = new System.Windows.Forms.ListBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.textBox2 = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // insLinks
            // 
            this.insLinks.FormattingEnabled = true;
            this.insLinks.ItemHeight = 24;
            this.insLinks.Location = new System.Drawing.Point(12, 84);
            this.insLinks.Name = "insLinks";
            this.insLinks.Size = new System.Drawing.Size(459, 580);
            this.insLinks.TabIndex = 0;
            // 
            // fileLinks
            // 
            this.fileLinks.FormattingEnabled = true;
            this.fileLinks.ItemHeight = 24;
            this.fileLinks.Location = new System.Drawing.Point(492, 84);
            this.fileLinks.Name = "fileLinks";
            this.fileLinks.Size = new System.Drawing.Size(536, 580);
            this.fileLinks.TabIndex = 2;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(50, 680);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(194, 25);
            this.label1.TabIndex = 3;
            this.label1.Text = "Внутренние ссылки";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(748, 680);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(169, 25);
            this.label3.TabIndex = 5;
            this.label3.Text = "Сылки на файлы";
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(265, 670);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(465, 91);
            this.button1.TabIndex = 6;
            this.button1.Text = "button1";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(12, 49);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(401, 29);
            this.textBox1.TabIndex = 7;
            this.textBox1.Text = "http://hpc.ssau.ru";
            // 
            // textBox2
            // 
            this.textBox2.Location = new System.Drawing.Point(492, 49);
            this.textBox2.Name = "textBox2";
            this.textBox2.Size = new System.Drawing.Size(401, 29);
            this.textBox2.TabIndex = 8;
            this.textBox2.Text = "100";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(12, 18);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(161, 25);
            this.label4.TabIndex = 9;
            this.label4.Text = "Введите ссылку";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(487, 18);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(277, 25);
            this.label5.TabIndex = 10;
            this.label5.Text = "Введите количество ссылок";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(11F, 24F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1098, 896);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.textBox2);
            this.Controls.Add(this.textBox1);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.fileLinks);
            this.Controls.Add(this.insLinks);
            this.MinimumSize = new System.Drawing.Size(1122, 960);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox insLinks;
        private System.Windows.Forms.ListBox fileLinks;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.TextBox textBox2;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
    }
}

