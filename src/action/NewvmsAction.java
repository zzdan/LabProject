package action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.struts2.json.annotations.JSON;
import org.glassfish.jersey.internal.util.Base64;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.model.compute.builder.BlockDeviceMappingBuilder;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.openstack.OSFactory;

import com.FileOperation;
import com.TestRunnable;
import com.TestRunnabletoo;
import com.model;
import com.opensymphony.xwork2.ActionSupport;
import com.mathworks.toolbox.javabuilder.MWException;
import com.opensymphony.xwork2.ActionContext;

import common.openstack.database.OpenstackVm;
import common.openstack.database.ImageChain;
import common.VM_select;
import Bptrain.Gettrainresult;
import common.openstack.databaseManager.DatabaseManagerApi;
import com.SuperInfo;
import com.SuperInsInfo;
import com.Supervisor;
import com.BPneuralnetwork;
import com.neuralnetwork;
import com.TestRunnable;

public class NewvmsAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String image;
	private String uu;
	private String pp;
	private String tt;
	private String flavor;
	private String flavorid;
	private int disk;
	private String hostname;
	private String neicun;
	private String neihe;
	private Integer[] software;
	private StringBuffer logininfo;

	/*
	 * 数据归一化 得到归一化之后的数据矩阵
	 */
	public static double[][] mapminmax(double x[][]) {
		double[][] x_map = new double[x.length][x[0].length];
		for (int i = 0; i < x.length; i++) {
			double xmax = Double.MIN_VALUE;
			double xmin = Double.MAX_VALUE;
			// 寻找最大最小值
			for (int j = 0; j < x[0].length; j++) {
				if (x[i][j] > xmax) {
					xmax = x[i][j];
				}
				if (x[i][j] < xmin) {
					xmin = x[i][j];
				}
			}
			// 利用最大最小值归一化
			for (int j = 0; j < x[0].length; j++) {
				x_map[i][j] = 2 * (x[i][j] - xmin) / (xmax - xmin) - 1;
			}
		}
		return x_map;
	}

	/*
	 * 测试数据和预测数据归一化使用相同的映射 输出每一行的最大最小值
	 */
	public static double[][] mapsame(double x[][]) {
		double out_same[][] = new double[x.length][2];
		for (int i = 0; i < x.length; i++) {
			double xmax = Double.MIN_VALUE;
			double xmin = Double.MAX_VALUE;
			// 寻找最大最小值
			for (int j = 0; j < x[0].length; j++) {
				if (x[i][j] > xmax) {
					xmax = x[i][j];
				}
				if (x[i][j] < xmin) {
					xmin = x[i][j];
				}
			}

			out_same[i][0] = xmax;
			out_same[i][1] = xmin;
		}
		return out_same;
	}

	/*
	 * 利用规范化的映射归一化数据
	 */
	public static double[][] mapsamereg(double x[][], double x_train_maxmin[][]) {
		double out_mapsamereg[][] = new double[x.length][x[0].length];
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
				out_mapsamereg[i][j] = 2 * (x[i][j] - x_train_maxmin[i][1])
						/ (x_train_maxmin[i][0] - x_train_maxmin[i][1]) - 1;
			}
		}
		return out_mapsamereg;
	}

	/*
	 * 矩阵转置函数 input x output x_T
	 */
	public static double[][] X_trans(double[][] x) {
		double[][] x_T = new double[x[0].length][x.length];
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
				x_T[j][i] = x[i][j];
			}
		}
		return x_T;
	}

	/*
	 * 矩阵复制成序列函数 input mat,num,axis output out_mat
	 */
	public static double[][] mat_seq(double[][] mat, int num, char axis) {
		if (axis == 'h') {
			double mat0[][] = new double[mat.length][num * (mat[0].length)];
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < num * (mat[0].length); j++) {
					mat0[i][j] = mat[i][j % (mat[0].length)];
				}
			}
			return mat0;
		} else {
			double mat0[][] = new double[num * mat.length][mat[0].length];
			for (int i = 0; i < num * mat.length; i++) {
				for (int j = 0; j < mat[0].length; j++) {
					mat0[i][j] = mat[i % mat.length][j];
				}
			}
			return mat0;
		}
	}

	/*
	 * 矩阵点乘 input x1,x2 output x_dot
	 */
	public static double[][] mat_dot(double[][] x1, double[][] x2) {
		double[][] x_dot = new double[x1.length][x1[0].length];
		for (int i = 0; i < x1.length; i++) {
			for (int j = 0; j < x1[0].length; j++) {
				x_dot[i][j] = x1[i][j] * x2[i][j];
			}
		}
		return x_dot;
	}

	/*
	 * 矩阵相乘
	 */
	public static double[][] mat_cal(double[][] x1, double[][] x2) {
		int lenx1a = x1.length;
		int lenx1b = x1[0].length;
		int lenx2a = x2.length;
		int lenx2b = x2[0].length;
		double[][] x_cal = new double[x1.length][x2[0].length];
		for (int i = 0; i < lenx1a; i++) {
			for (int j = 0; j < lenx2b; j++) {
				double sum = 0;
				for (int k = 0; k < lenx1b; k++) {
					sum += x1[i][k] * x2[k][j];
				}
				x_cal[i][j] = sum;
			}
		}
		return x_cal;
	}

	/*
	 * 1-H运算
	 */
	public static double[][] mat_H(double[][] H) {
		double[][] H_cal = new double[H.length][H[0].length];
		for (int i = 0; i < H.length; i++) {
			for (int j = 0; j < H[0].length; j++) {
				H_cal[i][j] = 1 - H[i][j];
			}
		}
		return H_cal;
	}

	/*
	 * 选取矩阵的第k行数据 返回矩阵
	 */
	public static double[][] mat_kdata(int k, double x[][]) {
		double[][] out_k = new double[1][x[0].length];
		for (int i = 0; i < x[0].length; i++) {
			out_k[0][i] = x[k][i];
		}
		return out_k;
	}

	/*
	 * 矩阵相减函数
	 */
	public static double[][] mat_dec(double[][] x1, double[][] x2) {
		int a1 = x1.length;
		int a2 = x1[0].length;
		int b1 = x2.length;
		int b2 = x2[0].length;
		double[][] out_dec = new double[a1][a2];
		for (int i = 0; i < a1; i++) {
			for (int j = 0; j < a2; j++) {
				out_dec[i][j] = x1[i][j] - x2[i][j];
			}
		}
		return out_dec;
	}

	/*
	 * 矩阵相加函数
	 */
	public static double[][] mat_inc(double[][] x1, double[][] x2) {
		int a1 = x1.length;
		int a2 = x1[0].length;
		int b1 = x2.length;
		int b2 = x2[0].length;
		double[][] out_inc = new double[a1][a2];
		for (int i = 0; i < a1; i++) {
			for (int j = 0; j < a2; j++) {
				out_inc[i][j] = x1[i][j] + x2[i][j];
			}
		}
		return out_inc;
	}

	/*
	 * 常数乘以矩阵
	 */
	public static double[][] mat_ncal(double alpha, double[][] x) {
		double[][] out_ncal = new double[x.length][x[0].length];
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
				out_ncal[i][j] = alpha * x[i][j];
			}
		}
		return out_ncal;
	}

	/*
	 * 矩阵每个值求其平方并计算其和
	 */
	public static double mat_sqrtsum(double x[][]) {
		double sum = 0;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
				sum = sum + Math.pow(x[i][j], 2);
			}
		}
		return sum;
	}

	/*
	 * 训练函数(更新权值阈值) input: 样本数据x,y 网络结构 隐含层大小,最大迭代次数
	 */
	public static void BP_train(int hiden_num, int maxgen, double x[][], double y[][]) {
		// 样本个数
		int sample_size = x.length;
		// 参数个数
		int n = x[0].length;
		int B1 = n;
		int B2 = hiden_num;
		int B3 = y[0].length;
		int maxgen_case = maxgen;
		// 初始化权值阈值，使用-1到1的随机数
		double ran;
		double[][] W12 = new double[B1][B2];
		double[][] b2 = new double[1][B2];
		double[][] W23 = new double[B2][B3];
		double[][] b3 = new double[1][B3];
		for (int i = 0; i < B1; i++) {
			for (int j = 0; j < B2; j++) {
				ran = 2 * Math.random() - 1;
				W12[i][j] = ran;
			}
		}

		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < B2; j++) {
				ran = 2 * Math.random() - 1;
				b2[i][j] = ran;
			}
		}
		for (int i = 0; i < B2; i++) {
			for (int j = 0; j < B3; j++) {
				ran = 2 * Math.random() - 1;
				W23[i][j] = ran;
			}
		}
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < B3; j++) {
				ran = 2 * Math.random() - 1;
				b3[i][j] = ran;
			}
		}
		// 动量因子
		double mc = 0.9;
		// 记录上一次权阈值的变化
		double change_W12 = 0;
		double change_b2 = 0;
		double change_W23 = 0;
		double change_b3 = 0;
		double[] E = new double[maxgen_case];
		double[][] H = new double[1][B2];
		double[][] O = new double[1][B3];
		double[][] delta_W12 = new double[B1][B2];
		double[][] delta_b2 = new double[1][B2];
		double[][] delta_W23 = new double[B2][B3];
		double[][] delta_b3 = new double[1][B3];
		// 更新权阈值
		double e = 0;
		double alpha = 0;
		for (int i = 0; i < maxgen_case; i++) {
			e = 0;
			for (int j = 0; j < sample_size; j++) {
				// alpha=0.001;
				alpha = 0.5 * Math.random();
				// 计算H
				H = mat_inc(mat_cal(mat_kdata(j, x), W12), b2);
				for (int h1 = 0; h1 < H.length; h1++) {
					for (int h2 = 0; h2 < H[0].length; h2++) {
						H[h1][h2] = 1 / (1 + Math.exp(-H[h1][h2]));
					}
				}
				// 计算O
				O = mat_inc(mat_cal(H, W23), b3);
				// 计算J对W12的偏导
				delta_W12 = mat_dot(
						mat_dot(mat_seq(X_trans(mat_kdata(j, x)), B2, 'h'), mat_seq(mat_dot(H, mat_H(H)), B1, 'v')),
						mat_seq(mat_cal(mat_dec(O, mat_kdata(j, y)), X_trans(W23)), B1, 'v'));
				// 计算J对b2的偏导
				delta_b2 = mat_dot(mat_dot(H, mat_H(H)), mat_cal(mat_dec(O, mat_kdata(j, y)), X_trans(W23)));
				// 计算J对W23的偏导
				delta_W23 = mat_dot(mat_seq(X_trans(H), B3, 'h'), mat_seq(mat_dec(O, mat_kdata(j, y)), B2, 'v'));
				// 计算J对b3的偏导
				delta_b3 = mat_dec(O, mat_kdata(j, y));
				// 更新权阈值
				W12 = mat_dec(W12, mat_ncal(alpha, delta_W12));
				b2 = mat_dec(b2, mat_ncal(alpha, delta_b2));
				W23 = mat_dec(W23, mat_ncal(alpha, delta_W23));
				b3 = mat_dec(b3, mat_ncal(alpha, delta_b3));
				e = e + mat_sqrtsum(mat_dec(O, mat_kdata(j, y)));
				// e=e+Math.abs(O[0][0]-y[j][0])/y[j][0];
			}

			E[i] = e;
			double sample = sample_size;
			// E[i]=e/sample;
			// System.out.println("迭代次数:"+Integer.toString(i+1));
			// System.out.println("均方误差:"+Double.toString(e/sample));
			/*
			 * if((e/sample)<=0.01) { model.W12=W12; model.b2=b2; model.W23=W23;
			 * model.b3=b3; model.E=E; break; }
			 */
		}
		model.W12 = W12;
		model.b2 = b2;
		model.W23 = W23;
		model.b3 = b3;
		model.E = E;
	}

	/*
	 * 预测函数 input: 权阈值 测试数据 output: 预测值
	 */
	public static double[][] BP_predict(double W12[][], double b2[][], double W23[][], double b3[][], double x[][]) {
		int m = x.length;
		double y_predict[][] = new double[m][1];
		for (int i = 0; i < m; i++) {
			y_predict[i][0] = 0;
		}
		double H[][] = new double[b2.length][b2[0].length];
		double O[][] = new double[b3.length][b3[0].length];
		for (int i = 0; i < m; i++) {
			H = mat_inc(mat_cal(mat_kdata(i, x), W12), b2);
			/*
			 * for(int j=0;j<m;j++) { H[0][j]=1/(1+Math.exp(-H[0][j])); }
			 */
			for (int h1 = 0; h1 < H.length; h1++) {
				for (int h2 = 0; h2 < H[0].length; h2++) {
					H[h1][h2] = 1 / (1 + Math.exp(-H[h1][h2]));
				}
			}
			O = mat_inc(mat_cal(H, W23), b3);
			y_predict[i][0] = O[i][0];
		}
		return y_predict;
	}

	/*
	 * 在线学习，对待学习库的数据进行权值、阈值的更新 从txt文件中读取待学习数据进行学习训练 输出：W12 b2 W23 b3
	 */
	public static void Online_train(File xfile, File yfile) {
		// 原来的初始样本
		double x_start[][] = {
				{ 22.2, 32.31, 44.7, 35.2, 35.9, 33.3, 41.8, 38.4, 49.3, 40.9, 55.1, 63.9, 48.4, 64.4, 54.7, 47.9, 44.3,
						43.8, 41.2, 35.2, 46.2, 39.3, 49.5, 61.2, 64, 39.9, 43.5, 44.4, 48.7 },
				{ 31.2, 36.5, 41.6, 46.8, 59.2, 69.1, 64.4, 90.9, 77.4, 62.9, 62.9, 72.4, 57.1, 67.3, 77.6, 67.3, 50,
						31.2, 36.5, 41.6, 46.8, 50, 57.1, 72.4, 77.6, 87.1, 96.9, 85.3, 95.7 },
				{ 28.7, 28.7, 28.7, 28.7, 33.5, 35.8, 23.3, 28.7, 26.5, 68.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7,
						28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 38.5, 39.8, 27.8, 31 },
				{ 59.8, 118, 106.3, 110.5, 82.8, 84.8, 68.6, 133.6, 179.3, 77.1, 48.1, 44.1, 58.1, 50.9, 56.3, 130.9,
						90, 65.4, 82.4, 92.4, 74.6, 91.2, 144.6, 128.7, 73.5, 195.5, 252, 213.4, 249.8 } };
		double y_start[][] = { { 0.256, 0.342, 0.346, 0.522, 0.632, 0.611, 0.74, 0.82, 0.567, 0.733, 0.95, 1.372, 1.049,
				1.52, 1.524, 0.718, 0.657, 0.617, 0.602, 0.549, 0.822, 0.666, 0.604, 1.06, 1.792, 0.6, 0.607, 0.571,
				0.578 } };
		// 从x测试文件集file中解析出二维数据出来
		double x_result[][] = null;
		try {
			x_result = FileOperation.readTxtFile(xfile, 'x');
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 原来初始样本和待学习库的样本一起作为训练集训练
		double x_start_tran[][] = X_trans(x_start);
		double x_sum[][] = new double[x_result.length + x_start_tran.length][x_result[0].length];
		for (int i = 0; i < (x_result.length + x_start_tran.length); i++) {
			for (int j = 0; j < (x_result[0].length); j++) {
				if (i < x_result.length) {
					x_sum[i][j] = x_result[i][j];
				} else {
					x_sum[i][j] = x_start_tran[i - x_result.length][j];
				}
			}
		}
		double x_train[][] = X_trans(x_sum);

		// 从y测试文件集file中解析出二维数据出来
		double y_result[][] = null;
		try {
			y_result = FileOperation.readTxtFile(yfile, 'y');
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 原来初始样本和待学习库的样本一起作为训练集训练
		double y_start_tran[][] = X_trans(y_start);
		double y_sum[][] = new double[y_result.length + y_start_tran.length][y_result[0].length];
		for (int i = 0; i < (y_result.length + y_start_tran.length); i++) {
			for (int j = 0; j < (y_result[0].length); j++) {
				if (i < y_result.length) {
					y_sum[i][j] = y_result[i][j];
				} else {
					y_sum[i][j] = y_start_tran[i - y_result.length][j];
				}
			}
		}

		double y_train[][] = X_trans(y_sum);
		double x_train_regular[][] = mapminmax(x_train);
		// 训练数据更新权值、阈值
		BP_train(10, 1000, X_trans(x_train_regular), X_trans(y_train));
	}

	/*
	 * 对学习库的x数据进行归一化映射，为了测试数据统一映射
	 */
	public static double[][] File_mapsame(File file) {
		// 从x测试文件集file中解析出二维数据出来
		double x_result[][] = null;
		// 原来的初始样本
		double x_start[][] = {
				{ 22.2, 32.31, 44.7, 35.2, 35.9, 33.3, 41.8, 38.4, 49.3, 40.9, 55.1, 63.9, 48.4, 64.4, 54.7, 47.9, 44.3,
						43.8, 41.2, 35.2, 46.2, 39.3, 49.5, 61.2, 64, 39.9, 43.5, 44.4, 48.7 },
				{ 31.2, 36.5, 41.6, 46.8, 59.2, 69.1, 64.4, 90.9, 77.4, 62.9, 62.9, 72.4, 57.1, 67.3, 77.6, 67.3, 50,
						31.2, 36.5, 41.6, 46.8, 50, 57.1, 72.4, 77.6, 87.1, 96.9, 85.3, 95.7 },
				{ 28.7, 28.7, 28.7, 28.7, 33.5, 35.8, 23.3, 28.7, 26.5, 68.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7,
						28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 38.5, 39.8, 27.8, 31 },
				{ 59.8, 118, 106.3, 110.5, 82.8, 84.8, 68.6, 133.6, 179.3, 77.1, 48.1, 44.1, 58.1, 50.9, 56.3, 130.9,
						90, 65.4, 82.4, 92.4, 74.6, 91.2, 144.6, 128.7, 73.5, 195.5, 252, 213.4, 249.8 } };
		try {
			x_result = FileOperation.readTxtFile(file, 'x');
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double x_start_tran[][] = X_trans(x_start);
		double x_sum[][] = new double[x_result.length + x_start_tran.length][x_result[0].length];
		for (int i = 0; i < (x_result.length + x_start_tran.length); i++) {
			for (int j = 0; j < (x_result[0].length); j++) {
				if (i < x_result.length) {
					x_sum[i][j] = x_result[i][j];
				} else {
					x_sum[i][j] = x_start_tran[i - x_result.length][j];
				}
			}
		}
		double x_train[][] = X_trans(x_sum);
		double x_train_maxmin[][] = mapsame(x_train);
		return x_train_maxmin;
	}

	@JSON(serialize = false)
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	@JSON(serialize = false)
	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	@JSON(serialize = false)
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@JSON(serialize = false)
	public Integer[] getSoftware() {
		return software;
	}

	public void setSoftware(Integer[] software) {
		this.software = software;
	}

	public String execute() throws Exception {
		// 获取用户请求信息
		System.out.println(hostname);
		System.out.println(flavor);
		System.out.println(image);
		System.out.println(software.length);
		TestRunnabletoo.hostname=hostname;
		TestRunnabletoo.flavor=flavor;
		TestRunnabletoo.image=image;
		TestRunnabletoo.software=software;
		ExecutorService fixedThreadPool=Executors.newFixedThreadPool(2);
		for (int i = 0; i < 100; i++) {
			final int index=i;
			fixedThreadPool.execute(new TestRunnabletoo());
		/*	Thread thread = new Thread(new TestRunnable());
			thread.start();*/
			
		}
		fixedThreadPool.shutdown();

		
		
		
/*		String systemname="";
		List<String> softtarget = new ArrayList<String>();
		try {
			if (flavor.equals("低配")) {
				flavorid = "2";
				neicun = "2048MB";
				neihe = "1";
				disk = 20;
			} else if (flavor.equals("中配")) {
				flavorid = "3";
				neicun = "4096MB";
				neihe = "2";
				disk = 40;
			} else if (flavor.equals("高配")) {
				flavorid = "4";
				neicun = "8192MB";
				neihe = "4";
				disk = 80;
			}
			if (image.equals("0")) {
				systemname = "ubuntu14";
				softtarget.add(systemname);
				for (int i = 0; i < software.length; i++) {
					switch (software[i]) {
					case 0:
						softtarget.add("mysql");
						break;
					case 1:
						softtarget.add("java");
						break;
					default:
						break;
					}
				}
			} else if (image.equals("1")) {
				systemname = "centos7";
				softtarget.add(systemname);
				for (int i = 0; i < software.length; i++) {
					switch (software[i]) {
					case 0:
						softtarget.add("mysql");
						break;
					case 1:
						softtarget.add("tomcat");
						break;
					default:
						break;
					}
				}
			} else if (image.equals("2")) {
				systemname = "win7";
				softtarget.add(systemname);
				for (int i = 0; i < software.length; i++) {
					switch (software[i]) {
					case 0:
						softtarget.add("VNCView");
						break;
					case 1:
						softtarget.add("百度云管家");
						break;
					default:
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < softtarget.size(); i++) {
			System.out.println("用户请求软件:" + softtarget.get(i));
		}
		// 获取当前用户的镜像列表，判断符合用户需求的镜像
		Iterator it1 = DatabaseManagerApi.getImage("zehang");
		ImageChain data2;
		String target1 = "";
		String soft = "";
		List<String> softdeal = new ArrayList<String>();
		List<String> imageid = new ArrayList<String>();
		while (it1.hasNext()) {
			data2 = (ImageChain) it1.next();
			soft = data2.getSoftlist();
			Iterator it2 = softtarget.iterator();
			int flag = 1;
			while (it2.hasNext()) {
				target1 = (String) it2.next();
				if (soft.indexOf(target1) >= 0) {
					flag = flag * 1;
				} else
					flag = flag * 0;
			}
			if (flag == 1) {
				softdeal.add(data2.getSoftimage());
				imageid.add(data2.getImageid());
			}
		}
	
		for (int i = 0; i < softdeal.size(); i++) {
			System.out.println("符合条件的镜像:" + softdeal.get(i)+" "+"镜像id"+imageid.get(i));
		}
			
		// 获取当前符合用户请求的虚拟机列表
		OpenstackVm data1;
		String imagesource = "";
		boolean flag1 = false;
		Iterator it3 = DatabaseManagerApi.getVM("zehang");
		List<VM_select> selectvm = new ArrayList<VM_select>();
		int k = 1;
		while (it3.hasNext()) {
			flag1 = false;
			data1 = (OpenstackVm) it3.next();
			imagesource = data1.getSystem();
			for (int j = 0; j < softdeal.size(); j++) {
				if (softdeal.get(j).equals(imagesource)) {
					flag1 = true;
				}
			}
			if (flag1 == true) {
				VM_select vmselect = new VM_select();
				vmselect.setInstanceName(data1.getName());
				vmselect.setIpadress(data1.getIp());
				vmselect.setNumber(k);
				selectvm.add(vmselect);
				System.out.println("符合条件的虚拟机:" + selectvm.toString());
				k++;
			}
		}
		// 获取虚拟机ip信息，作为监控函数的输入
		Iterator it4 = selectvm.iterator();
		VM_select data4;
		List<String> ipList = new ArrayList<>();
		while (it4.hasNext()) {
			data4 = (VM_select) it4.next();
			ipList.add(data4.getIpadress());
		}
		int ip_number = ipList.size();
		for (int m = 0; m < ip_number; m++) {
			System.out.println("虚拟机ip:" + ipList.get(m));
		}
		// 监控虚拟机状况，BP神经网络预测响应时间
		double[] timeresult = new double[ip_number];
		int i = 0;
		double cpuuse;
		double memuse;
		double diskuse;
		double netuse;
		// 训练集
		double x_train[][] = {
				{ 22.2, 32.31, 44.7, 35.2, 35.9, 33.3, 41.8, 38.4, 49.3, 40.9, 55.1, 63.9, 48.4, 64.4, 54.7, 47.9, 44.3,
						43.8, 41.2, 35.2, 46.2, 39.3, 49.5, 61.2, 64, 39.9, 43.5, 44.4, 48.7 },
				{ 31.2, 36.5, 41.6, 46.8, 59.2, 69.1, 64.4, 90.9, 77.4, 62.9, 62.9, 72.4, 57.1, 67.3, 77.6, 67.3, 50,
						31.2, 36.5, 41.6, 46.8, 50, 57.1, 72.4, 77.6, 87.1, 96.9, 85.3, 95.7 },
				{ 28.7, 28.7, 28.7, 28.7, 33.5, 35.8, 23.3, 28.7, 26.5, 68.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7,
						28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 28.7, 38.5, 39.8, 27.8, 31 },
				{ 59.8, 118, 106.3, 110.5, 82.8, 84.8, 68.6, 133.6, 179.3, 77.1, 48.1, 44.1, 58.1, 50.9, 56.3, 130.9,
						90, 65.4, 82.4, 92.4, 74.6, 91.2, 144.6, 128.7, 73.5, 195.5, 252, 213.4, 249.8 } };
		double y_train[][] = { { 0.256, 0.342, 0.346, 0.522, 0.632, 0.611, 0.74, 0.82, 0.567, 0.733, 0.95, 1.372, 1.049,
				1.52, 1.524, 0.718, 0.657, 0.617, 0.602, 0.549, 0.822, 0.666, 0.604, 1.06, 1.792, 0.6, 0.607, 0.571,
				0.578 } };
		double x_train_regular[][] = mapminmax(x_train);
		double x_train_maxmin[][] = mapsame(x_train);
		double x_t[][] = X_trans(x_train_regular);
		double y_t[][] = X_trans(y_train);
		// 初始时没有待学习库，此时用原有的29个样本数据训练
		BP_train(10, 1000, X_trans(x_train_regular), X_trans(y_train));
		// 记录插入到待学习库的数据的数量
		double learndatanum = 0;
		// 记录用户使用的次数
		double usenum = 0;
		// 判断用户是否发起请求
		boolean request = false;
		// 期望时间，若小于则使用对应虚拟机，否则重开虚拟机
		double t0 = 0.5;
		double time_min = t0;
		String ip_target = "";
		int j = 0;
		// 判断是否用过待学习库的数据训练过，如果是，那么归一化数据映射需要加上待学习库的数据
		boolean uselearn = false;
		try {
			List<SuperInsInfo> monitordata = Supervisor.getInsAVE(ipList);
			for (SuperInsInfo info : monitordata) {
				cpuuse = info.getCpu_use() / info.getCpu_total() * 100;
				memuse = info.getMemory_use() / info.getMemory_total() * 100;
				diskuse = info.getDisk_use() / info.getDisk_total() * 100;
				netuse = info.getInnet()/125;
				double[] array = { cpuuse, memuse, diskuse, netuse };
				System.out.println(array[0] + " " + array[1] + " " + array[2] + " " + array[3]);
				usenum = usenum + 1;
				double x_test[][] = { { 44 }, { 50 }, { 28 }, { 110 } };
				x_test[0][0] = array[0];
				x_test[1][0] = array[1];
				x_test[2][0] = array[2];
				x_test[3][0] = array[3];
				double x_test_regular[][];
				if (uselearn == false) {
					x_train_maxmin = mapsame(x_train);
					x_test_regular = mapsamereg(x_test, x_train_maxmin);
				} else {
					// 测试数据与待学习库和原来样本组成的数据要统一归一化映射
					File xfile = new File("C:/Users/moview/data.txt");
					x_train_maxmin = File_mapsame(xfile);// 得到归一化映射规则
					x_test_regular = mapsamereg(x_test, x_train_maxmin);
				}
				// 预测结果
				double y_test_predict[][] = BP_predict(model.W12, model.b2, model.W23, model.b3,
						X_trans(x_test_regular));
				System.out.println("预测结果为：" + Double.toString(y_test_predict[0][0]));
				if (y_test_predict[0][0] <= t0) {
					System.out.print("预测时间小于允许时间，该虚拟机符合用户需求\r\n");
					timeresult[i] = (double) y_test_predict[0][0];
					if (timeresult[i] <= time_min) {
						time_min = timeresult[i];
						ip_target = ipList.get(j);

					}
					i++;
				}
				j++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(time_min!=t0){
			System.out.println("目标虚拟机ip:" + ip_target);
			System.out.println("最短响应时间:" + time_min);
		}
		else{
			System.out.println("新建虚拟机");
			OSClient os = OSFactory.builder()
					.endpoint("http://116.56.140.61:5000/v2.0")
					.credentials("zehang","zehang")
					.tenantName("zehang")
					.authenticate();
			logininfo=new StringBuffer();
			if(systemname.equals("ubuntu14")){
				logininfo.append("#!/bin/sh\n");
				logininfo.append("passwd ubuntu<<EOF\n");
				logininfo.append("ubuntu\n");
				logininfo.append("ubuntu\n");
				logininfo.append("EOF\n");
				logininfo.append("sed -i 's/PasswordAuthentication no/PasswordAuthentication yes/g' /etc/ssh/sshd_config\n");
				logininfo.append("service ssh restart\n");
			}else if (systemname.equals("centos7")) {
				logininfo.append("#!/bin/sh\n");
				logininfo.append("passwd centos<<EOF\n");
				logininfo.append("centos\n");
				logininfo.append("centos\n");
				logininfo.append("EOF\n");
				logininfo.append("sed -i 's/PasswordAuthentication no/PasswordAuthentication yes/g' /etc/ssh/sshd_config\n");
				logininfo.append("service ssh restart\n");
			}
			List<String> net =new ArrayList<String>();
			net.add("0e029cc2-e03a-4816-af3f-0dc051859737");
			String userdata=Base64.encodeAsString(logininfo.toString().getBytes());
			System.out.println(hostname+" "+flavorid+" "+" "+imageid.get(0)+" "+softdeal.get(0));
			System.out.println(userdata);
			String ip="";
			try{
				ServerCreate sc = Builders.server().name(hostname).userData(userdata).flavor(flavorid).networks(net).image(imageid.get(0)).build();
				Server server=os.compute().servers().boot(sc);
				ip=os.compute().servers().list().get(0).getAddresses().getAddresses().get("private").get(0).getAddr();
			    DatabaseManagerApi.addVm("zehang", hostname, ip, neihe, neicun, softdeal.get(0), server.getId());;
			}catch (NullPointerException e) {
				e.printStackTrace();
			}
			
		}*/
		return SUCCESS;
	}

}
