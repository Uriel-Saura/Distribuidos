class threads2 extends Thread
{
	static long n;
	public void run()
	{
		for(int i = 0; i < 100000; i++)
            n++;
	}
	public static void main(String[]args) throws Exception
	{
        threads2 t1 = new threads2();
        threads2 t2 = new threads2();
		t1.start();
		t2.start();
		t1.join();///barrera
		t2.join();///barrera
		System.out.println(n);
	}
}