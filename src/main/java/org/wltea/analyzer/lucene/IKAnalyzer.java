package org.wltea.analyzer.lucene;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

/**
 * IK分词器，Lucene Analyzer接口实现
 */
@SuppressWarnings("unused")
public final class IKAnalyzer extends Analyzer{
	
	private boolean useSmart;
	
	private boolean useSmart() {
		return useSmart;
	}

	public void setUseSmart(boolean useSmart) {
		this.useSmart = useSmart;
	}

	/**
	 * IK分词器Lucene  Analyzer接口实现类
	 * 
	 * 默认细粒度切分算法
	 */
	public IKAnalyzer(){
		this(false);
	}
	
	/**
	 * IK分词器Lucene Analyzer接口实现类
	 * 
	 * @param useSmart 当为true时，分词器进行智能切分
	 */
	public IKAnalyzer(boolean useSmart){
		super();
		this.useSmart = useSmart;
	}



	@Override
	protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
		Tokenizer _IKTokenizer = new IKTokenizer(this.useSmart(),reader);
		return new TokenStreamComponents(_IKTokenizer);
	}

}
