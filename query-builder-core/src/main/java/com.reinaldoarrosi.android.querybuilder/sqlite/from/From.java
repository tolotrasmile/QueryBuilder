package com.reinaldoarrosi.android.querybuilder.sqlite.from;

import java.util.List;

import com.reinaldoarrosi.android.querybuilder.sqlite.QueryBuilder;
import com.reinaldoarrosi.android.querybuilder.sqlite.criteria.Criteria;
import com.reinaldoarrosi.android.querybuilder.sqlite.projection.Projection;

public abstract class From {
	public static class PartialJoin {
		private String joinType;
		private From left;
		private From right;

		protected PartialJoin(From left, From right, String joinType) {
			this.joinType = joinType;
			this.left = left;
			this.right = right;
		}
		
		public JoinFrom on(String leftColumn, String rightColumn) {
			return on(Criteria.equals(Projection.column(leftColumn), Projection.column(rightColumn)));
		}
		
		public JoinFrom on(Criteria criteria) {
			return new JoinFrom(left, right, joinType, criteria);
		}
	}
	
	public static TableFrom table(String table) {
		return new TableFrom(table);
	}
	
	public static SubQueryFrom subQuery(QueryBuilder subQuery) {
		return new SubQueryFrom(subQuery);
	}
	
	public PartialJoin innerJoin(String table) {
		return innerJoin(From.table(table));
	}
	
	public PartialJoin innerJoin(QueryBuilder subQuery) {
		return innerJoin(From.subQuery(subQuery));
	}
	
	public PartialJoin innerJoin(From table) {
		return new PartialJoin(this, table, "INNER JOIN");
	}
	
	public PartialJoin leftJoin(String table) {
		return leftJoin(From.table(table));
	}
	
	public PartialJoin leftJoin(QueryBuilder subQuery) {
		return leftJoin(From.subQuery(subQuery));
	}
	
	public PartialJoin leftJoin(From table) {
		return new PartialJoin(this, table, "LEFT JOIN");
	}
	
	public abstract String build();
	
	public abstract List<Object> buildParameters();
}
