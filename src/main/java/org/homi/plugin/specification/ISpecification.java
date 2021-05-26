package org.homi.plugin.specification;

import java.util.List;

/**
 * 
 * @author Nicolas Hanout
 *
 * @since 0.0.1
 */
public interface ISpecification {
	
	/**
	 * 
	 * @return
	 */
	public String name();
	
	/**
	 * 
	 * @return
	 */
	public List<TypeDef<?>> getParameterTypes();
	
	/**
	 * 
	 * @return
	 */
	public TypeDef<?> getReturnType();;
	
}
