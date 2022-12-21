using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace lab2MIVS
{
    internal class Potok
    {
        double a;
        double b;
        public double t=0;
        double l => a * t + b;
       
        public double[] GetTime(double[] R)
        {
            double[] times = new double[1000];
            t = 0;
            for (int i = 0; i < 1000; i++)
            {
                times[i] = Math.Abs(-((1 / a) * (a * t + b)) + (1 / a) * Math.Sqrt(Math.Pow((a * t + b),2) - (2 * a * Math.Log(R[i]))));
                t += times[i];
            }
            Array.Sort(times);
            return times;

        }

        public double[] GetExp()
        {
            int n = 1000;
            double a = 0.5;
            double[] R = new double[1000];

            Random r = new Random();
            for (int i = 0; i < n; i++)
            {
                R[i] = Math.Abs(-(Math.Log(r.NextDouble()/l))/l);
            }
            Array.Sort(R);
            Array.Reverse(R);
            return R;

        }
        public Potok(double a, double b)
        {
            this.a = a;
            this.b = b;
        }
        public void Start()
        {
          
            GetTime(GetExp());
        }
    }
}
