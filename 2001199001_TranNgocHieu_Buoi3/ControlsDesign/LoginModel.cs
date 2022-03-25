using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using SqlClass;

namespace ControlsDesign
{
    public partial class LoginModel : UserControl
    {
        public string stringConnection, sql;
        
        public LoginModel()
        {
            InitializeComponent();
        }

        private void login1_Load(object sender, EventArgs e)
        {
            
        }
    }
}
