package com.app.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class QueryBuilder {

	/**
	 * クエリ文字列のリスト
	 */
	StringBuilder queryString;

	/**
	 * パラメータのkey部分を保持するリスト
	 */
	private List<String> paramNameList;

	/**
	 * パラメータのvalue部分を保持するリスト
	 */
	private List<Object> paramValueList;

	/**
	 * コンストラクタ
	 * クエリ・パラメータを格納するリストを初期化する
	 * @param em
	 */
	public QueryBuilder() {
		this.queryString = new StringBuilder();
		this.paramNameList = new ArrayList<String>();
		this.paramValueList = new ArrayList<Object>();
	}

	/**
	 * クエリ文字列をリストに追加する。
	 * 連続で追加できるよう、自身を返す
	 * @param inputQuery 入力されたクエリ文字列
	 * @return QueryBuilder
	 */
	public QueryBuilder append(String inputQuery) {
		this.queryString.append(inputQuery);
		this.queryString.append(" ");

		return this;
	}

	/**
	 * クエリオブジェクトにセットするためのパラメータを格納する。
	 * @param key 「:name」のような形で与えられるSQLへの埋め込み箇所を特定するための文字列　
	 * @param value　実際にセットされるパラメータ
	 * @return 連続してセットできるよう自身を返す
	 */
	public QueryBuilder setParam(String key, Object value) {
		this.paramNameList.add(key);
		this.paramValueList.add(value);

		return this;
	}

	/**
	 * クエリオブジェクトを作成する
	 * @param entityClass 結果セットが属するエンティティクラス
	 * @return Queryインスタンス
	 */
	public Query createQuery(Class<?> entityClass, EntityManager em) {
		Query query = em.createNativeQuery(queryString.toString(), entityClass);
		//クエリ文字列の「:」プレースホルダが付与されたパラメータへ値を設定
		for (int i = 0; i < this.paramNameList.size(); i++) {
			query.setParameter(this.paramNameList.get(i), this.paramValueList.get(i));
		}

		return query;
	}
}
