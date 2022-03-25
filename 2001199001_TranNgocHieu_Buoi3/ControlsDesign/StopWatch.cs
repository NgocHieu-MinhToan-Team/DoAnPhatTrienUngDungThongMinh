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
    public partial class StopWatch : UserControl
    {
        public StopWatch()
        {
            InitializeComponent();
        }

        System.Diagnostics.Stopwatch stopwatch = new System.Diagnostics.Stopwatch();
        private void button1_Click(object sender, EventArgs e)
        {
            if(btnStart.Text=="Start")
            {
                btnStart.Text = "Stop";
                timer1.Start();
                this.stopwatch.Start();
            }
            else
            {
                btnStart.Text = "Start";
                timer1.Stop();
                this.stopwatch.Stop();
            }
        }

        private void btnLap_Click(object sender, EventArgs e)
        {
            if(btnStart.Text=="Start")
            {
                MessageBox.Show("You must start the clock first!");
                return;
            }
            else
            {

                //MessageBox.Show(lblTime.Text);
                File.AppendAllText("E:\\LuuDuLieuSinhVien\\TNH\\2001199001_TranNgocHieu_Buoi3\\ControlsDesign\\bin\\Debug\\output.txt", lblTime.Text);
                File.AppendAllText("E:\\LuuDuLieuSinhVien\\TNH\\2001199001_TranNgocHieu_Buoi3\\ControlsDesign\\bin\\Debug\\output.txt","\r\n");
                
                return;
            }
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            TimeSpan elapsed = this.stopwatch.Elapsed;
            lblTime.Text = string.Format("{0:00}:{1:00}:{2:00}:{3:00}", Math.Floor(elapsed.TotalHours), elapsed.Minutes, elapsed.Seconds, elapsed.Milliseconds);
            //lblTime.Text = elapsed.ToString();
            //lblTime.Text = this.stopwatch.Elapsed.ToString();
        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            this.stopwatch.Reset();
            lblTime.Text = "00:00:00:000";
            if (btnStart.Text == "Stop")
                btnStart.Text = "Start";
        }
    }
}
