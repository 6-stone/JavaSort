import java.util.Random;

//���ڼƽϵ������㷨��������ʵ����Comparable<>�ӿ�
//��ʵ��Comparable<>�ӿڣ��Զ�������compareTo����������Date.
class Date implements Comparable<Date>{
	int year,month,day;
	public Date(int year,int month,int day){
		this.year=year;
		this.month=month;
		this.day=day;
	}
	public int compareTo(Date that){
		if(this.year>that.year) return 1;
		if(this.year<that.year) return -1;
		if(this.month>that.month) return 1;
		if(this.month<that.month) return -1;
		if(this.day>that.day) return 1;
		if(this.day<that.day) return -1;
		return 0;
	}
	public String toString(){
		return year+"/"+month+"/"+day+" ";
	}
	
}

//���ڱȽ϶���������㷨
class Sort{
	public static boolean compareLess(Comparable a,Comparable b){
		if(a.compareTo(b)<0) return true;
		return false;
	}
	public static void swap(Comparable[] a,int i,int j){
		Comparable tmp;
		tmp=a[i];
		a[i]=a[j];
		a[j]=tmp;
	}
	public static void selectionSort(Comparable[] a){
		for(int i=0;i<a.length-1;i++){
			int minIndex=i;
			for(int j=i+1;j<a.length;j++){
				if(compareLess(a[j],a[minIndex]))
					minIndex=j;
			}
			swap(a,minIndex,i);
		}
		
	}
	public static void bubbleSort(Comparable[] a){
		//���ð�����
		for(int i=1;i<a.length;i++)
			for(int j=0;j<a.length-i;j++){
				if(compareLess(a[j+1],a[j]))
					swap(a,j,j+1);
			}
//		for(int i=1;i<a.length;i++)
//			for(int j=i;j<a.length;j++){
//				if(compareLess(a[j],a[j-1]))
//					swap(a,j,j-1);
//			}
	}
	public static void insertionSort(Comparable[] a){
//        ԭʼ�������򣬽���������Ԫ�أ��Ľ����ڽ�����ʹ�÷��������������
//		for(int i=1;i<a.length;i++){
//			for(int j=i;j>0&&compareLess(a[j],a[j-1]);j--)
//				swap(a,j,j-1);
		for(int i=1;i<a.length;i++){
			Comparable tmp=a[i];
			int j;
			for(j=i;j>0&&compareLess(tmp, a[j-1]);j--)
				a[j]=a[j-1];
			a[j]=tmp;
		}
	}
	public static void shellSort(Comparable[] a){
		int h=1;
		//ʹ�õ�������1,4,13,40....
		while(h<a.length/3) h=3*h+1;
		while(h>=1){
			for(int i=h;i<a.length;i++)
				for(int j=i;j>=h&&compareLess(a[j],a[j-h]);j-=h)
					swap(a,j,j-h);
			h=h/3;
		}
		
	}
}


//�Զ����¹鲢���򣬲��õݹ鷽ʽ�ͷ���˼�룻
//�ݹ��ŵ��㷨����׶���ȱ��Ч�ʵͣ����׷���ջ���
//ÿ���̶߳���һ��ջ�����÷���ʱ��pushһ��ջ֡���ݹ����push����ջ֡���������
class MergeSort {
	private static Comparable[] tmp;
	public static void sort(Comparable[] a){
		tmp=new Comparable[a.length]; 
		sort(a,0,a.length-1);
	}
	//ԭ�ع鲢ʵ��
	private static void merge(Comparable[] a,int lo,int mid,int hi){
		for(int i=lo;i<=hi;i++)
			tmp[i]=a[i];
		int i=lo, j=mid+1;
		for(int k=lo;k<=hi;k++){
			if(i>mid) a[k]=tmp[j++];
			else if(j>hi) a[k]=tmp[i++];
			else if(tmp[i].compareTo(tmp[j])<0) a[k]=tmp[i++];
			else a[k]=tmp[j++];
		}
	}
	private static void sort(Comparable[] a,int lo,int hi){
		if(lo>=hi) return ;
//		С����ݹ��Ƶ�����÷�����Ч�ʵͣ����ò�������
		//if(hi<=lo+15)Sort.insertionSort(a��lo.hi);
		//return ;
		int mid=lo+(hi-lo)/2;
		sort(a,lo,mid);
		sort(a,mid+1,hi);
		merge(a,lo,mid,hi);
	}
	private static void sortBU(Comparable[] a){
		for(int size=1;size<a.length;size*=2){
			for(int lo=0;lo<a.length-size;lo+=2*size){
				merge(a,lo,lo+size-1,Math.min(lo+2*size-1,a.length-1));
			}
		}
	}

}

class QuickSort{
	public static void sort(Comparable[] a){
		sort(a,0,a.length-1);
	}
	private static void sort(Comparable[] a,int lo,int hi){
		if(lo>=hi) return;
//		�Ľ����ɵ�����Ԫ��С��16ʱ��ʹ�ò�������
//		if(hi-lo<16)
//			Sort.insertionSort(a,lo,hi);
//      return ;
//		�ݹ�������С����ʱҲ��Ƶ�����ã�Ӱ������
//		����Ľ���������������λ������ŦԪ
//		�Ľ��������������Ϊ������ŦԪ��С����ŦԪ��������ŦԪ����
		int v=partition(a,lo,hi);
		sort(a,lo,v-1);
		sort(a,v+1,hi);
	}
	private static int partition(Comparable[] a,int lo,int hi){
		int i=lo;
		//ѡ��һ��Ԫ��a[lo]����ŦԪ
		int j=hi+1;
		while(true){
			while(a[++i].compareTo(a[lo])<0) if(i>=hi) break;
			while(a[--j].compareTo(a[lo])>0) ;//if(j<=lo) break;
			if(i>=j) break;
			else Sort.swap(a,i,j);
		}
		Sort.swap(a, lo, j);
		return j;
		
		
	}
}


class heapSort{
//	public static void sort(Comparable[] a){
//		int N=a.length;
//		for(int i=N/2;i>=0;i--){
//			sink(a,i,N);
//		}
//		while(N>1){
//			swap(a,0,N-1);
//			N--;
//			sink(a,0,N);
//		}
//	}
	public static void sort(Comparable[] a){
		int N=a.length-1;
		for(int i=N/2;i>0;i--){
			sink(a,i,N);
		}
		while(N>1){
			swap(a,1,N--);
			sink(a,1,N);
		}
	}
	private static void swap(Comparable[] a,int i,int j){
		Comparable tmp=a[i];
		a[i]=a[j];
		a[j]=tmp;
	}
	private static void sink(Comparable[] a,int i,int N){
		while(2*i<=N){
			int j=2*i;
		    if(j+1<=N&&a[j].compareTo(a[j+1])<0) j++;
		    if(a[i].compareTo(a[j])>=0) break;
		    swap(a,i,j);
		    i=j;
		}
	}
}

//����
public class SortTest{
	private static Integer[] num={3,543,3,52,42,56,1,2354,43,65};
	static String[] string={"File","Edit","Source","Refactor","Navigate","search","Project","Run","Window","Help"};
	static Random r1=new Random(46);
	static Random r2=new Random(46);
	static Random r3=new Random(46);
	//static������ʱ�����ʼ��
	private static Date[] date={new Date(2011,2,21),new Date(2011,4,12),
			new Date(r1.nextInt(2017)+1,r2.nextInt(12)+1,r3.nextInt(30)+1),
			new Date(r1.nextInt(2017)+1,r2.nextInt(12)+1,r3.nextInt(30)+1),
			new Date(r1.nextInt(2017)+1,r2.nextInt(12)+1,r3.nextInt(30)+1)};
//	private  Date[] date2=new Date[10];
//	for(int i=0;i<num.length;i++){
//		date2[i]=new Date(r1.nextInt(2017)+1,r2.nextInt(12)+1,r3.nextInt(30)+1);
//		}
	private static Integer[] shellnum={r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000)};
	
	private static Integer[] mergenumUB={r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000)};
	private static Integer[] mergenumBU={r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000)};
	private static Integer[] quicknum={r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000)};
	private static Integer[]heapnum={null,r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),
			r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000),r1.nextInt(1000)};
	
	public static void main(String[] args){
		Sort.selectionSort(num);
		Sort.bubbleSort(string);
		Sort.insertionSort(date);
		Sort.shellSort(shellnum);
		MergeSort.sort(mergenumUB);
		MergeSort.sort(mergenumBU);
		QuickSort.sort(quicknum);
		heapSort.sort(heapnum);
//		assert isSorted(num);
//		assert isSorted(string);
//		assert isSorted(date);
//		assert isSorted(shellnum);
		print(num);
		print(string);
		print(date);
		print(shellnum);
		print(mergenumUB);
		print(mergenumBU);
		print(quicknum);
		print(heapnum);
	}
	public static void print(Comparable[] a){
		for(Comparable obj:a){
			System.out.print(obj+" ");
		}
		System.out.println();
	}
	public static boolean isSorted(Comparable[] a){
		for(int i=1;i<a.length;i++){
			if(Sort.compareLess(a[i],a[i-1]))
					return false;
		}
		return true;
	}
}
