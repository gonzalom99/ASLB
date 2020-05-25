package matrizindividual;

import net.sf.jclec.ISpecies;
import net.sf.jclec.base.AbstractRecombinator;

/**
 * OrderArrayIndividual (and subclasses) specific recombinator.  
 * 
 * @author Alberto Cano
 * @author Jose Maria Luna
 * @author Juan Luis Olmo
 * @author Amelia Zafra
 * @author Sebastian Ventura
 */

public abstract class MatrizRecombinator extends AbstractRecombinator 
{
	/////////////////////////////////////////////////////////////////
	// --------------------------------------------------- Attributes
	/////////////////////////////////////////////////////////////////
	
	private static final long serialVersionUID = -285985989834167887L;
	
	/** Individual species */
	
	protected IMatrizSpecies species;
	
	/////////////////////////////////////////////////////////////////
	// ------------------------------------------------- Constructors
	/////////////////////////////////////////////////////////////////
	
	/**
	 * Empty constructor.
	 */
	
	public MatrizRecombinator() 
	{
		super();
	}

	/////////////////////////////////////////////////////////////////
	// -------------------------------------------- Protected methods
	/////////////////////////////////////////////////////////////////	
	
	// AbstractRecombinator methods
	
	/**
	 * Sets ppl = 2
	 * 
	 * {@inheritDoc}
	 */
	
	@Override
	protected void setPpl() 
	{
		this.ppl = 2;
	}

	/**
	 * Sets spl = 2
	 * 
	 * {@inheritDoc}
	 */

	@Override
	protected void setSpl() 
	{
		this.spl = 2;
	}

	/**
	 * {@inheritDoc}
	 */
	
	@Override	
	protected void prepareRecombination()
	{
		// Sets individual species
		ISpecies spc = context.getSpecies();
		if (spc instanceof IMatrizSpecies) {
			this.species = (IMatrizSpecies) spc;
		}
		else {
			throw new IllegalStateException("Invalid population species");
		}		
	}
}
