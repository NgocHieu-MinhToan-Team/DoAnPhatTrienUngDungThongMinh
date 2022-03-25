using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;

namespace ControlsDesign
{
    public partial class InfoStudent : UserControl
    {
        private string filepath;

        public string Filepath
        {
            //get { return filepath; }
            set { filepath = value; }
        }
        public InfoStudent()
        {
            InitializeComponent();
        }
         
        private void InfoStudent_Load(object sender, EventArgs e)
        {

        }

        private void numericTextBox1_KeyPress(object sender, KeyPressEventArgs e)
        {
            if(e.KeyChar==13)
            {
                
                string[] data = File.ReadAllLines(filepath);
                //string[] data = File.ReadAllLines("input.txt");
                for(int i=0;i< data.Length;i++)
                {
                    string[] info = data[i].Split(',');
                    if(txtMSV.Text==info[0])
                    {
                        lblTen.Text = info[1];
                        lblLop.Text = info[2];
                        lblNgaySinh.Text = info[3];
                        lblGioiTinh.Text = info[4];
                        lblCMND.Text = info[5];
                        return;
                    }
                }
                MessageBox.Show("Khong tim thay thong tin sinh vien co ma so: " + txtMSV.Text);
            }
        }

        private void txtMSV_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
