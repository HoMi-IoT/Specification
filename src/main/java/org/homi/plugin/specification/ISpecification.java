package org.homi.plugin.specification;

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
	public Type<?>[] getParameterTypes();
	
	/**
	 * 
	 * @return
	 */
	public Type<?> getReturnType();;
	
}
