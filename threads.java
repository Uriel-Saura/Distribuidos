class threads extends Thread
{
	static long n;
    static Object obj = new Object();
	public void run()
	{
		for(int i = 0; i < 100000; i++)
            synchronized(obj){
                n++;
            }
	}
	public static void main(String[]args) throws Exception
	{
        threads t1 = new threads();
        threads t2 = new threads();
		t1.start();
		t2.start();
		t1.join();///barrera
		t2.join();///barrera
		System.out.println(n);
	}
}