package matrizindividual;

import net.sf.jclec.util.intset.IIntegerSet;

/**
 * Abstract implementation for IOrderArraySpecies.
 *
 * @author Alberto Cano
 * @author Jose Maria Luna
 * @author Juan Luis Olmo
 * @author Amelia Zafra
 * @author Sebastian Ventura
 */

public abstract class AbstractMatrizSpecies implements IMatrizSpecies
{
	/////////////////////////////////////////////////////////////////
	// --------------------------------------------------- Properties
	/////////////////////////////////////////////////////////////////

	private static final long serialVersionUID = -2860189329462902082L;
	
	/** Schema */

	protected IIntegerSet [][] genotypeSchema;

	/////////////////////////////////////////////////////////////////
	// ------------------------------------------------- Constructors
	/////////////////////////////////////////////////////////////////

	/**
	 * Empty constructor
	 */

	public AbstractMatrizSpecies()
	{
		super();
	}

	/////////////////////////////////////////////////////////////////
	// ----------------------------------------------- Public methods
	/////////////////////////////////////////////////////////////////

	/**
	 * {@inheritDoc}
	 */

	public int getGenotypeLength()
	{
		return genotypeSchema.length;
	}

	/**
	 * {@inheritDoc}
	 */

	public IIntegerSet [][] getGenotypeSchema()
	{
		return genotypeSchema;
	}
}
