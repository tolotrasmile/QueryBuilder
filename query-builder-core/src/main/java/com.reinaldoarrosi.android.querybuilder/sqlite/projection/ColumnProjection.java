package com.reinaldoarrosi.android.querybuilder.sqlite.projection;

import java.util.List;

import com.reinaldoarrosi.android.querybuilder.Utils;

public class ColumnProjection extends Projection {
	private String table;
	private String column;
	
	public ColumnProjection(String table, String column) {
		this.table = table;
		this.column = column;
	}

	@Override
	public String build() {
		String ret = "";
		
		if(!Utils.isNullOrWhiteSpace(table))
			ret = ret + table + ".";
		
		if(!Utils.isNullOrWhiteSpace(column))
			ret = ret + column;
		
		return ret;
	}

	@Override
	public List<Object> buildParameters() {
		return Utils.EMPTY_LIST;
	}
}
