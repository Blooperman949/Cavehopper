package me.bluper.cavehopper.registries;

import me.bluper.cavehopper.Cavehopper;

public enum Blocks
{
	AIR("air", Cavehopper.NAMESPACE),
	
	CRUMBROCK("crumbrock", Cavehopper.NAMESPACE),
	SILT("silt", Cavehopper.NAMESPACE),
	
	VULROCK("vulrock", Cavehopper.NAMESPACE);
	
	public final String id;
	public final String namespace;
	public final String displayName;
	Blocks(String id, String namespace)
	{
		this.id = id;
		this.namespace = namespace;
		this.displayName = Texts.valueOf("BLOCK_" + id.toUpperCase()).get();
	}
	
	public String getNamespacedID()
	{
		return namespace + ':' + id;
	}
}
