namespace ControlsDesign
{
    partial class LoginModel
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

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.login1 = new ControlsDesign.Login();
            this.SuspendLayout();
            // 
            // login1
            // 
            this.login1.Location = new System.Drawing.Point(33, 17);
            this.login1.Name = "login1";
            this.login1.Size = new System.Drawing.Size(279, 150);
            this.login1.TabIndex = 0;
            this.login1.Load += new System.EventHandler(this.login1_Load);
            // 
            // LoginModel
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.login1);
            this.Name = "LoginModel";
            this.Size = new System.Drawing.Size(330, 192);
            this.ResumeLayout(false);

        }

        #endregion

        private Login login1;
    }
}
