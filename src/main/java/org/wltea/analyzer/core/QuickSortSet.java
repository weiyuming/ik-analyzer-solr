package org.wltea.analyzer.core;

/**
 * IK分词器专用的Lexem快速排序集合
 */
class QuickSortSet {
	//链表头
	private Cell head;
	//链表尾
	private Cell tail;
	//链表的实际大小
	private int size;
	
	QuickSortSet(){
		this.size = 0;
	}
	
	/**
	 * 向链表集合添加词元
	 */
	void addLexeme(Lexeme lexeme){
		Cell newCell = new Cell(lexeme); 
		if(this.size == 0){
			this.head = newCell;
			this.tail = newCell;
			this.size++;

		}else{
			/*if(this.tail.compareTo(newCell) == 0){//词元与尾部词元相同，不放入集合

			}else */if(this.tail.compareTo(newCell) < 0){//词元接入链表尾部
				this.tail.next = newCell;
				newCell.prev = this.tail;
				this.tail = newCell;
				this.size++;

			}else if(this.head.compareTo(newCell) > 0){//词元接入链表头部
				this.head.prev = newCell;
				newCell.next = this.head;
				this.head = newCell;
				this.size++;

			}else{					
				//从尾部上逆
				Cell index = this.tail;
				while(index != null && index.compareTo(newCell) > 0){
					index = index.prev;
				}
				/*if(index.compareTo(newCell) == 0){//词元与集合中的词元重复，不放入集合

				}else */if((index != null ? index.compareTo(newCell) : 1) < 0){//词元插入链表中的某个位置
					newCell.prev = index;
					newCell.next = index.next;
					index.next.prev = newCell;
					index.next = newCell;
					this.size++;
				}
			}
		}
	}
	
	/**
	 * 返回链表头部元素
	 */
	Lexeme peekFirst(){
		if(this.head != null){
			return this.head.lexeme;
		}
		return null;
	}
	
	/**
	 * 取出链表集合的第一个元素
	 * @return Lexeme
	 */
	Lexeme pollFirst(){
		if(this.size == 1){
			Lexeme first = this.head.lexeme;
			this.head = null;
			this.tail = null;
			this.size--;
			return first;
		}else if(this.size > 1){
			Lexeme first = this.head.lexeme;
			this.head = this.head.next;
			this.size --;
			return first;
		}else{
			return null;
		}
	}
	
	/**
	 * 返回链表尾部元素
	 */
	Lexeme peekLast(){
		if(this.tail != null){
			return this.tail.lexeme;
		}
		return null;
	}
	
	/**
	 * 取出链表集合的最后一个元素
	 * @return Lexeme
	 */
	Lexeme pollLast(){
		if(this.size == 1){
			Lexeme last = this.head.lexeme;
			this.head = null;
			this.tail = null;
			this.size--;
			return last;
			
		}else if(this.size > 1){
			Lexeme last = this.tail.lexeme;
			this.tail = this.tail.prev;
			this.size--;
			return last;
			
		}else{
			return null;
		}
	}
	
	/**
	 * 返回集合大小
	 */
	int size(){
		return this.size;
	}
	
	/**
	 * 判断集合是否为空
	 */
	boolean isEmpty(){
		return this.size == 0;
	}
	
	/**
	 * 返回lexeme链的头部
	 */
	Cell getHead(){
		return this.head;
	}

	/*
	 * IK 中文分词  版本 7.0
	 * IK Analyzer release 7.0
	 * update by Magese(magese@live.cn)
	 */
	@SuppressWarnings("unused")
	class Cell implements Comparable<Cell>{
		private Cell prev;
		private Cell next;
		private Lexeme lexeme;
		
		Cell(Lexeme lexeme){
			if(lexeme == null){
				throw new IllegalArgumentException("lexeme must not be null");
			}
			this.lexeme = lexeme;
		}

		public int compareTo(Cell o) {
			return this.lexeme.compareTo(o.lexeme);
		}

		public Cell getPrev(){
			return this.prev;
		}
		
		Cell getNext(){
			return this.next;
		}
		
		public Lexeme getLexeme(){
			return this.lexeme;
		}
	}
}
