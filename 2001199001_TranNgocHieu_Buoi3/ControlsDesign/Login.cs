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
    public partial class Login : UserControl
    {
        public string stringConnection, sql, table, namefield, passfield;
        //khai bao day du string connection, ten bang, ten cot usn, ten cot pwd trong db truoc khi su dung
        private void btnThoat_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        sqlClass connect = new sqlClass();
        public Login()
        {
            InitializeComponent();
        }

        private void btnDangNhap_Click(object sender, EventArgs e)
        {
            if(stringConnection==null|| table==null||namefield==null||passfield==null)
            {
                MessageBox.Show("Vui long khai bao day du thuoc tinh truoc khi su dung!");
                return;
            }
            connect.CreateConnection(stringConnection);
            sql = "SELECT * FROM "+table+" WHERE "+namefield+" = "+txtTenDN+" AND "+passfield+" = "+txtMatKhau;
            DataTable db = connect.ExcuteQuery(sql);
            if(db==null)
            {
                MessageBox.Show("Dang nhap that bai!");
                return;
            }
            else
            {
                MessageBox.Show("Dang nhap thanh cong!");
            }
        }
    }
}
