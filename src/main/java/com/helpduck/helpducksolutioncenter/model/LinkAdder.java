package com.helpduck.helpducksolutioncenter.model;

import org.springframework.data.domain.Page;

public interface LinkAdder<T> {
	public void addLink(Page<T> list);

	public void addLink(T object);
}