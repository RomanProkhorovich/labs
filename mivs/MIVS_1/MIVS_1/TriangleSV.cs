namespace MIVS_1
{
    internal  class TriangleSV
    {
        public  int a { get; set; } = 1;
        public  int b { get; set; } = 2;

        private double lambda = 23.7;
        private int intervals = 15;
        private double alpha = 0.05;

        public double[] Heights=> CountHeight();
        public double[] N = new double[16]; 
        public double[] n => GetIntervals();
        public int[] countIntervals => GetCountEveryInterval(GetRealiztion(), n);
        public double[] Mids => GetMid();
        double xee => GetXee(countIntervals);



        private  double[] GetIntervals()
        {//xz
            double[] result = new double[16]; 
            for (int i = 0; i < N.Length; i++)
            {
                result[i] = Math.Sqrt((double)i / (15) * a * (a + b)) - a;
                if (result[i] >= 0 && result[i] <= b)
                    result[i] = b - Math.Sqrt((1 - (double)i / (15)) * b * (a + b));
            }

            return result;
        }

        private double[] GetRealiztion()
        {
            double[] res = new double[1000];
            Random rnd = new Random();
            for (int i = 0; i < res.Length; i++)
            {
                double r = rnd.NextDouble();
                res[i] = Math.Sqrt(r * a * (a + b)) - a;
                if (res[i] >= 0 && res[i] <= b)
                    res[i] = b - Math.Sqrt((1 - r) * b * (a + b));

            }
            Array.Sort(res);
            return res;
        }

        private int[] GetCountEveryInterval(double[] realizations, double[] intervs)
        {
            int[] res = new int[15];
            int f = 0;
            for (int j = 0; j < 15; j++)
            {
                for (int i = 0; i < 1000; i++)
                {
                    if ((realizations[i] >= n[j]) && (realizations[i] <= n[j + 1]))
                    {
                        f++;
                    }
                }
                res[j] = f;
                f = 0;
            }
            return res;
        }

        private double GetXee(int[] countInEveryInterval)
        {
            double sum=0;
            for (int i = 0; i < countInEveryInterval.Length; i++)
            {
                sum +=Math.Pow(countInEveryInterval[i], 2);
            }
            return intervals/1000 * sum - 1000;
        }
        private double[] CountHeight()
        {
            double[] height=new double[intervals];
            for (int j = 0; j < height.Length; j++)
            {
                height[j] = countIntervals[j] / (1000 * (n[j + 1] - n[j]));
            }
            return height;
        }

        private double[] GetMid()
        {
            double[] res=new double[15];
            for (int i = 0; i < 15; i++)
            {
                res[i] = (n[i + 1] + n[i]) / 2;
            }
            return res;
        }


    }
}
