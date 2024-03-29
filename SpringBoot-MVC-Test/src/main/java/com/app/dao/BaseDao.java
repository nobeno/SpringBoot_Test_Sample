package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.app.entity.BaseEntity;

public class BaseDao<T extends BaseEntity> {

	@PersistenceContext
	private EntityManager em;

	//コンストラクタ
	public BaseDao() {
	}

	public EntityManager getEm() {
		return this.em;
	}

	/**
	 * エンティティをDBへ登録する
	 * @param entity DBへ登録する対象となるエンティティ
	 */
	public T saveOrUpdate(T entity) {

		T result = em.merge(entity);

		return result;
	}

	/**
	 * クエリの実行結果のリストを取得する
	 * @return Entityのリスト
	 */
	@SuppressWarnings("unchecked")
	public List<T> findResultList(Query query) {
		return query.getResultList();
	}

	/**
	 * クエリの実行結果の単一オブジェクトを取得する
	 * @return 結果セットがnull→null | 結果セットの単一オブジェクト
	 */
	@SuppressWarnings("unchecked")
	public T findSingle(Query query) {
		// EMで標準で用意されているgetSingleResultメソッドは結果セットが空の場合例外を投げるが、
		// 例外処理は呼び出し元あるいはサービスレイヤーで個別に行うべきなので、getResultListの結果で分岐させる
		List<T> result = query.getResultList();
		if (result.isEmpty()) {
			return null;
		}

		return result.get(0);
	}


}
