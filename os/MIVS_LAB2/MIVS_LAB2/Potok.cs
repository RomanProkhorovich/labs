using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MIVS_LAB2
{
    internal class Potok
    {
        private double lambda;
        private double alpha;

        private double MaxT;

        private double[] timesDif;

        public double[] GetTimesDif()
        {
            return timesDif;
        }


        public Potok(double maxT, double Alpha, double Lambda)
        {
            MaxT = maxT;
            alpha = Alpha;
            lambda = Lambda;
        }
        public double[] GetExp()
        {
            double[] R = new double[1000];

            Random r = new Random();
            for (int i = 0; i < R.Length; i++)
            {
                R[i] = Math.Abs(-(Math.Log(r.NextDouble() / lambda)) / lambda);
            }
            Array.Reverse(R);
            return R;

        }

        


        public double[] GetAVG()
        {

            double[] R = new double[1000];

            Random r = new Random();
            for (int i = 0; i < R.Length; i++)
            {
                R[i] = alpha * r.NextDouble() * (alpha - 1);
            }
            return R;
        }

        public List<double> Start(out int i)
        {
            var a = GetAVG();
            var b = GetExp();

            var res = a.Concat(b).ToArray();
            Array.Sort(res);
            timesDif = GetMas(res, 0, 999);
            double t = 0;

            List<double> times = new List<double>();
            i = 0;
            for (int ii = 0; ii< timesDif.Length; ii++)
            {
                t += timesDif[ii];
                times.Add(t);
            }
            int iii = 0;
            while (iii < times.Count&&times[iii] < 600 )
            {
                i++;
                iii++;
            }

            return times;

        }

        private double[] GetMas(double[]mas, int startIndex,int endIndex)
        {
            var res = new List<double>();
            for (int i = startIndex; i < endIndex; i++)
            {
                res.Add(mas[i]);
            }
            return res.ToArray();
        }
    }
}
