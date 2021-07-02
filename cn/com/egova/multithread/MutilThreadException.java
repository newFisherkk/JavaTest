package cn.com.egova.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MutilThreadException {
	public static void main(String[] args) {
		new MutilThreadException().testFutureException();
	}

	private void testFutureException(){
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		//results中存放任务执行的结果
		List<Future<Boolean>> results = new ArrayList<>(10);
		try {
			for (int i = 0; i < 10; i++) {
				//submit callable然后get 或者 execute runnable 都可以抛出异常信息，切记不可submit runnable
				Future<Boolean> future = executorService.submit(new ActSKUCacheCallable(i));
				results.add(future);
			}
			for (int i = 0; i < 10; i++) {
				//第一次调用get所有任务并发执行，但主线程获取flag仍是按顺序执行，i为0时返回true，i为1时抛出异常
				boolean flag = results.get(i).get();
				System.out.println("执行结果：" + flag);
			}
		} catch (Exception e) {
			System.out.println("最外层抛出:");
			e.printStackTrace();
		}
	}

	class ActSKUCacheCallable implements Callable<Boolean> {
		int i;
		ActSKUCacheCallable(int i){
			this.i = i;
		}
		public Boolean call() throws Exception {
			boolean flag = true;
			try {
				if(i % 2 != 0){
					throw new Exception("try:"+i);
				}
				System.out.println("正常执行:"+i);
			} catch (Exception e) {
				System.out.println("catch:"+e.getMessage());
				flag = false;
				//抛出异常不影响线程池中其他任务的执行
				throw e;
			} 
			return flag;
		}
	}
	
	
}



	

