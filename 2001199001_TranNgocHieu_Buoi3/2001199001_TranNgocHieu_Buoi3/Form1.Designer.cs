namespace _2001199001_TranNgocHieu_Buoi3
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.stopWatch1 = new ControlsDesign.StopWatch();
            this.infoStudent1 = new ControlsDesign.InfoStudent();
            this.SuspendLayout();
            // 
            // stopWatch1
            // 
            this.stopWatch1.Location = new System.Drawing.Point(113, 117);
            this.stopWatch1.Name = "stopWatch1";
            this.stopWatch1.Size = new System.Drawing.Size(341, 210);
            this.stopWatch1.TabIndex = 2;
            // 
            // infoStudent1
            // 
            this.infoStudent1.Location = new System.Drawing.Point(2, 2);
            this.infoStudent1.Name = "infoStudent1";
            this.infoStudent1.Size = new System.Drawing.Size(472, 117);
            this.infoStudent1.TabIndex = 0;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(550, 339);
            this.Controls.Add(this.stopWatch1);
            this.Controls.Add(this.infoStudent1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);

        }

        #endregion

        private ControlsDesign.InfoStudent infoStudent1;
        private ControlsDesign.StopWatch stopWatch1;
    }
}