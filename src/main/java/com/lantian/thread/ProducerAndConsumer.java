package com.lantian.thread;

import java.util.PriorityQueue;

public class ProducerAndConsumer {

	private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);
	
	public static void main(String[] args) {
		ProducerAndConsumer test = new ProducerAndConsumer();
		Producer producer = test.new Producer();
		Consumer consumer = test.new Consumer();
		
		producer.start();
		consumer.start();
	}
	
	class Consumer extends Thread{
		
		@Override
		public void run() {
			consume();
		}
		
		private void consume() {
			while(true){
				synchronized (queue) {
					while (queue.isEmpty()) {
						try {
							System.out.println("queue is empty,wait...");
							queue.wait();
						} catch (Exception e) {
							e.printStackTrace();
							queue.notify();
						}
					}
					queue.poll();
					queue.notify();
					System.out.println("consumer poll one element");
				}
			}
		}
	}
	
	class Producer extends Thread{
		
		@Override
		public void run() {
			produce();
		}
		
		private void produce() {
			while(true){
				synchronized (queue) {
					while(queue.size() == queueSize){
						try {
							System.out.println("queue is full,wait...");
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
							queue.notify();
						}
					}
					queue.offer(1);
					queue.notify();
					System.out.println("producer offer one element");
				}
			}
		}
	}

}










