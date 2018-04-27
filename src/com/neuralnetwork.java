package com;

public class neuralnetwork {
	
	
	/*
	 * 数据归一化
	 * 得到归一化之后的数据矩阵
	 */
	public static double[][] mapminmax(double x[][])
	{
		double [][]x_map=new double[x.length][x[0].length];
		for(int i=0;i<x.length;i++)
		{
			double xmax=Double.MIN_VALUE;
			double xmin=Double.MAX_VALUE;
            //寻找最大最小值
			for(int j=0;j<x[0].length;j++)
			{
				if(x[i][j]>xmax)
				{
					xmax=x[i][j];
				}
				if(x[i][j]<xmin)
				{
					xmin=x[i][j];
				}
			}
			//利用最大最小值归一化
			for(int j=0;j<x[0].length;j++)
			{
				x_map[i][j]=2*(x[i][j]-xmin)/(xmax-xmin)-1;
			}
		}
		return x_map;
	}
	/*
	 * 测试数据和预测数据归一化使用相同的映射
	 * 输出每一行的最大最小值
	 */
	public static double[][] mapsame(double x[][])
	{
		double out_same[][]=new double[x.length][2];
		for(int i=0;i<x.length;i++)
		{
			double xmax=Double.MIN_VALUE;
			double xmin=Double.MAX_VALUE;
            //寻找最大最小值
			for(int j=0;j<x[0].length;j++)
			{
				if(x[i][j]>xmax)
				{
					xmax=x[i][j];
				}
				if(x[i][j]<xmin)
				{
					xmin=x[i][j];
				}
			}
			
			out_same[i][0]=xmax;
			out_same[i][1]=xmin;
		}
		return out_same;
	}
	/*
	 * 利用规范化的映射归一化数据
	 */
	public static double[][] mapsamereg(double x[][],double x_train_maxmin[][])
	{
		double out_mapsamereg[][]=new double[x.length][x[0].length];
		for(int i=0;i<x.length;i++)
		{
			for(int j=0;j<x[0].length;j++)
			{
				out_mapsamereg[i][j]=2*(x[i][j]-x_train_maxmin[i][1])/(x_train_maxmin[i][0]-x_train_maxmin[i][1])-1;
			}
		}
		return out_mapsamereg;
	}
	
	/*
	 * 矩阵转置函数
	 * input x
	 * output x_T
	 */
	public static double[][] X_trans(double [][]x)
	{
		double [][]x_T=new double[x[0].length][x.length];
		for(int i=0;i<x.length;i++)
		{
			for(int j=0;j<x[0].length;j++)
			{
				x_T[j][i]=x[i][j];
			}
		}
		return x_T;
	}
	
	/*
	 * 矩阵复制成序列函数
	 * input mat,num,axis
	 * output out_mat
	 */
	public static double[][] mat_seq(double [][]mat,int num,char axis)
	{
		if(axis=='h')
		{
			double mat0[][]=new double[mat.length][num*(mat[0].length)];
			for(int i=0;i<mat.length;i++)
			{
				for(int j=0;j<num*(mat[0].length);j++)
				{
				   mat0[i][j]=mat[i][j%(mat[0].length)];
				}
			}
			return mat0;
		}
		else
		{
			double mat0[][]=new double[num*mat.length][mat[0].length];
			for(int i=0;i<num*mat.length;i++)
			{
				for(int j=0;j<mat[0].length;j++)
				{
					mat0[i][j]=mat[i%mat.length][j];
				}
			}
			return mat0;
		}
	}
	
	/*
	 * 矩阵点乘
	 * input x1,x2
	 * output x_dot
	 */
	public static  double[][] mat_dot(double [][]x1,double [][]x2)
	{
		double [][]x_dot=new double[x1.length][x1[0].length];
		for(int i=0;i<x1.length;i++)
		{
			for(int j=0;j<x1[0].length;j++)
			{
				x_dot[i][j]=x1[i][j]*x2[i][j];
			}
		}
		return x_dot;
	}
	/*
	 * 矩阵相乘
	 */
	public static double[][] mat_cal(double [][]x1,double [][]x2)
	{
		int lenx1a=x1.length;
		int lenx1b=x1[0].length;
		int lenx2a=x2.length;
		int lenx2b=x2[0].length;
		double[][] x_cal=new double[x1.length][x2[0].length];
		for(int i=0;i<lenx1a;i++)
		{
			for(int j=0;j<lenx2b;j++)
			{
				double sum=0;
				for(int k=0;k<lenx1b;k++)
				{
					sum=sum+x1[i][k]*x2[k][j];
				}
				x_cal[i][j]=sum;
			}
		}
		return x_cal;
	}
	/*
	 * 1-H运算
	 */
	public static double [][] mat_H(double [][]H)
	{
		double[][] H_cal=new double[H.length][H[0].length];
		for(int i=0;i<H.length;i++)
		{
			for(int j=0;j<H[0].length;j++)
			{
				H_cal[i][j]=1-H[i][j];
			}
		}
		return H_cal;
	}
	/*
	 * 选取矩阵的第k行数据
	 * 返回矩阵
	 */
	public static double[][] mat_kdata(int k,double x[][])
	{
		double [][]out_k=new double[1][x[0].length];
		for(int i=0;i<x[0].length;i++)
		{
			out_k[0][i]=x[k][i];
		}
		return out_k;
	}
	/*
	 * 矩阵相减函数
	 */
	public static double[][] mat_dec(double [][]x1,double [][]x2)
	{
		int a1=x1.length;
		int a2=x1[0].length;
		int b1=x2.length;
		int b2=x2[0].length;
		double [][]out_dec=new double[a1][a2];
		for(int i=0;i<a1;i++)
		{
			for(int j=0;j<a2;j++)
			{
				out_dec[i][j]=x1[i][j]-x2[i][j];
			}
		}
		return out_dec;
	}
	/*
	 * 矩阵相加函数
	 */
	public static double[][] mat_inc(double [][]x1,double [][]x2)
	{
		int a1=x1.length;
		int a2=x1[0].length;
		int b1=x2.length;
		int b2=x2[0].length;
		double [][]out_inc=new double[a1][a2];
		for(int i=0;i<a1;i++)
		{
			for(int j=0;j<a2;j++)
			{
				out_inc[i][j]=x1[i][j]+x2[i][j];
			}
		}
		return out_inc;
	}
	/*
	 * 常数乘以矩阵
	 */
	public static double[][] mat_ncal(double alpha,double [][]x)
	{
		double [][]out_ncal=new double[x.length][x[0].length];
		for(int i=0;i<x.length;i++)
		{
			for(int j=0;j<x[0].length;j++)
			{
				out_ncal[i][j]=alpha*x[i][j];
			}
		}
		return out_ncal;
	}
	/*
	 * 矩阵每个值求其平方并计算其和
	 */
	public static double mat_sqrtsum(double x[][])
	{
		double sum=0;
		for(int i=0;i<x.length;i++)
		{
			for(int j=0;j<x[0].length;j++)
			{
				sum=sum+Math.pow(x[i][j],2);
			}
		}
		return sum;
	}
	/*
	 * 训练函数(更新权值阈值)
	 * input: 样本数据x,y 网络结构 隐含层大小,最大迭代次数
	 */
	public static void BP_train(int hiden_num,int maxgen,double x[][],double y[][])
	{
		//样本个数
		int sample_size=x.length;
		System.out.print("样本大小："+sample_size+"\n");
		//参数个数
		int n=x[0].length;
		System.out.print("参数个数："+n+"\n");
		int B1=n;
		System.out.print("B1："+B1+"\n");
		int B2=hiden_num;
		System.out.print("B2："+B2);
		int B3=y[0].length;
		System.out.print("B3："+B3);
		int maxgen_case=maxgen;
		System.out.print("最大迭代数："+maxgen_case+"\n");
		//初始化权值阈值，使用-1到1的随机数
		double ran;
		double [][]W12 = new double[B1][B2];
		double [][]b2 = new double[1][B2];
		double [][]W23 = new double[B2][B3];
		double [][]b3 = new double[1][B3];
		System.out.println("W12初始化：\n");
		for(int i=0;i<B1;i++)
		{
			for(int j=0;j<B2;j++)				
			{
				ran=2*Math.random()-1;
				W12[i][j]=ran;
				System.out.print(W12[i][j]+" ");		
			}
			System.out.print("\n");
		}
		System.out.println("b2初始化：\n");
		for(int i=0;i<1;i++)
		{
			for(int j=0;j<B2;j++)				
			{
				ran=2*Math.random()-1;
				b2[i][j]=ran;
				System.out.print(b2[i][j]+" ");		
			}
			System.out.print("\n");
		}
		System.out.println("W23初始化：\n");
		for(int i=0;i<B2;i++)
		{
			for(int j=0;j<B3;j++)				
			{
				ran=2*Math.random()-1;
				W23[i][j]=ran;
				System.out.print(W23[i][j]+" ");		
			}
			System.out.print("\n");
		}
		System.out.println("b3初始化：\n");
		for(int i=0;i<1;i++)
		{
			for(int j=0;j<B3;j++)				
			{
				ran=2*Math.random()-1;
				b3[i][j]=ran;
				System.out.print(b3[i][j]+" ");
			}
			System.out.print("\n");
		}
		//动量因子
		double mc=0.9;
		//记录上一次权阈值的变化
		double change_W12=0;
		double change_b2=0;
		double change_W23=0;
		double change_b3=0;
		double []E=new double[maxgen_case];
		double [][]H = new double[1][B2];
		double [][]O = new double[1][B3];
		double [][]delta_W12=new double[B1][B2];
		double [][]delta_b2=new double[1][B2];
		double [][]delta_W23=new double[B2][B3];
		double [][]delta_b3=new double[1][B3];
		//更新权阈值
		double e=0;
		double alpha=0;
		for(int i=0;i<maxgen_case;i++)
		{
			e=0;
			for(int j=0;j<sample_size;j++)
			{
				//alpha=0.001;
				alpha=0.5*Math.random();
				System.out.print("alpha:"+alpha);
				//计算H
				System.out.print("H\n");
				/*for(int m=0;m<B2;m++)
				{
					double tmp=0;
					for(int t=0;t<B1;t++)
					{
						tmp=tmp+x[j][t]*W12[t][m];
					}
					H[0][m]=tmp+b2[0][m];
					
				}*/
				H=mat_inc(mat_cal(mat_kdata(j,x),W12),b2);
			/*	for(int m=0;m<B2;m++)
				{
					H[0][m]=1/(1+Math.exp(H[0][m]));
					System.out.print(H[0][m]+" ");
				}*/
				for(int h1=0;h1<H.length;h1++)
				{
					for(int h2=0;h2<H[0].length;h2++)
					{
						H[h1][h2]=1/(1+Math.exp(-H[h1][h2]));
						System.out.print(H[h1][h2]+" ");
					}
				}
				System.out.print("\n");
				//计算O
				System.out.print("O\n");
			/*	for(int m=0;m<B3;m++)
				{
					double tmp=0;
					for(int t=0;t<B2;t++)
					{
						tmp=tmp+H[0][t]*W23[t][m];
					}
					O[0][m]=tmp+b3[0][m];
					System.out.print(O[0][m]+" ");
				}
				System.out.print("\n");*/
				O=mat_inc(mat_cal(H,W23),b3);
				for(int o1=0;o1<O.length;o1++)
				{
					for(int o2=0;o2<O[0].length;o2++)
					{
						System.out.print(O[o1][o2]+" ");
					}
				}
				System.out.print("\n");
				
				//计算J对W12的偏导
				double temp_dot[][]=mat_dot(mat_seq(X_trans(mat_kdata(j,x)),B2,'h'),mat_seq(mat_dot(H,mat_H(H)),B1,'v'));
				System.out.print("temp_dot:");
				for(int i_w12=0;i_w12<temp_dot.length;i_w12++)
				{
					for(int j_w12=0;j_w12<temp_dot[0].length;j_w12++)
					{
						System.out.print(temp_dot[i_w12][j_w12]+" ");
					}
					System.out.print("\n");
				}
				System.out.print("\n");
				double dec_O_y[][]=mat_dec(O,mat_kdata(j,y));
				System.out.print("dec_O_y:");
				for(int i_w12=0;i_w12<dec_O_y.length;i_w12++)
				{
					for(int j_w12=0;j_w12<dec_O_y[0].length;j_w12++)
					{
						System.out.print(dec_O_y[i_w12][j_w12]+" ");
					}
					System.out.print("\n");
				}
				System.out.print("\n");
				double w23_tran[][]=X_trans(W23);
				System.out.print("w23_tran:");
				for(int i_w12=0;i_w12<w23_tran.length;i_w12++)
				{
					for(int j_w12=0;j_w12<w23_tran[0].length;j_w12++)
					{
						System.out.print(w23_tran[i_w12][j_w12]+" ");
					}
					System.out.print("\n");
				}
				System.out.print("\n");
				double cal_O_y[][]=mat_cal(dec_O_y,w23_tran);
				System.out.print("cal_O_y:");
				for(int i_w12=0;i_w12<cal_O_y.length;i_w12++)
				{
					for(int j_w12=0;j_w12<cal_O_y[0].length;j_w12++)
					{
						System.out.print(cal_O_y[i_w12][j_w12]+" ");
					}
					System.out.print("\n");
				}
				System.out.print("\n");
				double temp_dot2[][]=mat_seq(cal_O_y,B1,'v');
				System.out.print("temp_dot2:");
				for(int i_w12=0;i_w12<temp_dot2.length;i_w12++)
				{
					for(int j_w12=0;j_w12<temp_dot2[0].length;j_w12++)
					{
						System.out.print(temp_dot2[i_w12][j_w12]+" ");
					}
					System.out.print("\n");
				}
				System.out.print("\n");
				delta_W12=mat_dot(temp_dot,temp_dot2);
				System.out.print("delta_W12:");
				for(int i_w12=0;i_w12<delta_W12.length;i_w12++)
				{
					for(int j_w12=0;j_w12<delta_W12[0].length;j_w12++)
					{
						System.out.print(delta_W12[i_w12][j_w12]+" ");
					}
					System.out.print("\n");
				}
				System.out.print("\n");
				//计算J对b2的偏导
				delta_b2=mat_dot(mat_dot(H,mat_H(H)),mat_cal(mat_dec(O,mat_kdata(j,y)),X_trans(W23)));
				System.out.print("b2:");
				for(int i_w12=0;i_w12<delta_b2.length;i_w12++)
				{
					for(int j_w12=0;j_w12<delta_b2[0].length;j_w12++)
					{
						System.out.print(delta_b2[i_w12][j_w12]+" ");
					}
					System.out.print("\n");
				}
				System.out.print("\n");
				//计算J对W23的偏导
				delta_W23=mat_dot(mat_seq(X_trans(H),B3,'h'),mat_seq(mat_dec(O,mat_kdata(j,y)),B2,'v'));
				System.out.print("W23:");
				for(int i_w12=0;i_w12<delta_W23.length;i_w12++)
				{
					for(int j_w12=0;j_w12<delta_W23[0].length;j_w12++)
					{
						System.out.print(delta_W23[i_w12][j_w12]+" ");
					}
				}
				System.out.print("\n");
				//计算J对b3的偏导
				delta_b3=mat_dec(O,mat_kdata(j,y));
				System.out.print("b3:");
				for(int i_w12=0;i_w12<delta_b3.length;i_w12++)
				{
					for(int j_w12=0;j_w12<delta_b3[0].length;j_w12++)
					{
						System.out.print(delta_b3[i_w12][j_w12]+" ");
					}
				}
				System.out.print("\n");
				//更新权阈值
				W12=mat_dec(W12,mat_ncal(alpha,delta_W12));
				b2=mat_dec(b2,mat_ncal(alpha,delta_b2));
				W23=mat_dec(W23,mat_ncal(alpha,delta_W23));
				b3=mat_dec(b3,mat_ncal(alpha,delta_b3));
				e=e+mat_sqrtsum(mat_dec(O,mat_kdata(j,y)));
			}
			
			E[i]=e;
			System.out.println("迭代次数:"+Integer.toString(i));
		}		
		model.W12=W12;
		model.b2=b2;
		model.W23=W23;
		model.b3=b3;
		model.E=E;
	}
	
	/*
	 * 预测函数
	 * input: 权阈值  测试数据
	 * output: 预测值
	 */
	public static double[][] BP_predict(double W12[][],double b2[][],double W23[][],double b3[][],double x[][])
	{
		int m=x.length;
		double y_predict[][]=new double[m][1];
		for(int i=0;i<m;i++)
		{
			y_predict[i][0]=0;
		}
		double H[][]=new double[b2.length][b2[0].length];
		double O[][]=new double[b3.length][b3[0].length];
		for(int i=0;i<m;i++)
		{
			H=mat_inc(mat_cal(mat_kdata(i,x),W12),b2);
			/*for(int j=0;j<m;j++)
			{
				H[0][j]=1/(1+Math.exp(-H[0][j]));
			}*/
			for(int h1=0;h1<H.length;h1++)
			{
				for(int h2=0;h2<H[0].length;h2++)
				{
					H[h1][h2]=1/(1+Math.exp(-H[h1][h2]));
				}
			}
			O=mat_inc(mat_cal(H,W23),b3);
			y_predict[i][0]=O[i][0];
		}
		return y_predict;
	}
	
	/*public static void main(String[] args)
    {
		//测试训练集
		double x_train[][]= { {22.2,32.31 ,44.7, 35.2 ,35.9, 33.3, 41.8, 38.4 ,49.3, 40.9 ,55.1, 63.9, 48.4 ,64.4, 54.7, 47.9, 44.3, 43.8, 41.2, 35.2, 46.2, 39.3, 49.5, 61.2, 64 ,39.9, 43.5 ,44.4, 48.7},
				{ 31.2, 36.5, 41.6, 46.8, 59.2, 69.1, 64.4, 90.9, 77.4, 62.9, 62.9, 72.4, 57.1, 67.3, 77.6, 67.3 ,50 ,31.2, 36.5, 41.6, 46.8, 50, 57.1, 72.4, 77.6, 87.1, 96.9, 85.3, 95.7},
	      {28.7, 28.7, 28.7, 28.7, 33.5, 35.8, 23.3, 28.7, 26.5, 68.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 38.5, 39.8, 27.8, 31},
	      { 59.8, 118, 106.3, 110.5, 82.8, 84.8, 68.6, 133.6, 179.3, 77.1, 48.1, 44.1, 58.1 ,50.9, 56.3, 130.9, 90 ,65.4, 82.4, 92.4, 74.6, 91.2, 144.6 ,128.7 ,73.5, 195.5 ,252, 213.4, 249.8}} ; 
		double x_test[][]={{44},{50},{28},{110}};
		double y_train[][]={{0.256,0.342, 0.346 ,0.522, 0.632, 0.611, 0.74, 0.82, 0.567, 0.733, 0.95, 1.372, 1.049, 1.52, 1.524, 0.718, 0.657, 0.617, 0.602, 0.549, 0.822, 0.666, 0.604, 1.06, 1.792, 0.6 ,0.607, 0.571, 0.578}};
		double x_train_regular[][]=mapminmax(x_train);
		for(int i=0;i<x_train_regular.length;i++)
		{
			for(int j=0;j<x_train_regular[0].length;j++)
			{
				System.out.print(x_train_regular[i][j]+" ");
			}
			 System.out.println("\n");
		}
		   System.out.println("----------------------");
		double x_train_maxmin[][]=mapsame(x_train);
		for(int i=0;i<x_train_maxmin.length;i++)
		{
			for(int j=0;j<2;j++)
			{
				System.out.print(x_train_maxmin[i][j]+" ");
			}
			 System.out.println("\n");
		}
		 System.out.println("----------------------");
		double x_test_regular[][]=mapsamereg(x_test,x_train_maxmin);
		for(int i=0;i<x_test_regular.length;i++)
		{
			for(int j=0;j<x_test_regular[0].length;j++)
			{
				System.out.print(x_test_regular[i][j]+" ");
			}
			 System.out.println("\n");
		}
		System.out.println("----------------------");
		double x_t[][]=X_trans(x_train_regular);
		for(int i=0;i<x_t.length;i++)
		{
			for(int j=0;j<x_t[0].length;j++)
			{
				System.out.print(x_t[i][j]+" ");
			}
			 System.out.println("\n");
		}
		System.out.println("----------------------");
		double y_t[][]=X_trans(y_train);
		for(int i=0;i<y_t.length;i++)
		{
			for(int j=0;j<y_t[0].length;j++)
			{
				System.out.print(y_t[i][j]+" ");
			}
			 System.out.println("\n");
		}
		System.out.println("----------------------");
		double x_test1[][]={{0.07470362604833158 }};
		
		double x_test2[][]={{0.17440278360094155,0.1310794830605492 ,0.44543440419495 ,-0.3164108494647983, -0.6524507440875795, -1.1581477731123742 ,-0.9756496972937646, 0.5104811820242661 ,-0.6427979109847755 ,-0.49071054349467624 }};
		
		double x_result[][]=mat_cal(x_test1,x_test2);
		for(int i=0;i<x_result.length;i++)
		{
			for(int j=0;j<x_result[0].length;j++)
			{
				System.out.print(x_result[i][j]+" ");
			}
			 System.out.println("\n");
		}
		double a=0.07470362604833158;
		double b=0.17440278360094155;
		double c=a*b;
		System.out.print(c);
	  // BP_train(10,1,X_trans(x_train_regular),X_trans(y_train));
	   // double y_test_predict[][]=BP_predict(model.W12,model.b2,model.W23,model.b3,X_trans(x_test_regular));
	 //   System.out.println("预测结果为："+Double.toString(y_test_predict[0][0]));
    }*/
	

}
