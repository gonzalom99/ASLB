package matrizindividual;

import net.sf.jclec.ISpecies;

import net.sf.jclec.base.AbstractMutator;
import net.sf.jclec.util.intset.IIntegerSet;

/**
 * OrderArrayIndividual (and subclasses) specific mutator.  
 * 
 * @author Alberto Cano
 * @author Jose Maria Luna
 * @author Juan Luis Olmo
 * @author Amelia Zafra
 * @author Sebastian Ventura
 */

public abstract class MatrizMutator extends AbstractMutator
{
	/////////////////////////////////////////////////////////////////
	// ------------------------------------------- Internal variables
	/////////////////////////////////////////////////////////////////

	private static final long serialVersionUID = 1606583104193731031L;

	/** Individuals species */
	
	protected transient IMatrizSpecies species; 

	/** Individuals schema */
	
	protected transient IIntegerSet [][] schema;
	
	/////////////////////////////////////////////////////////////////
	// ------------------------------------------------- Constructors
	/////////////////////////////////////////////////////////////////
	
	/**
	 * Empty constructor
	 */
	
	public MatrizMutator() 
	{
		super();
	}

	/////////////////////////////////////////////////////////////////
	// -------------------------------------------- Protected methods
	/////////////////////////////////////////////////////////////////
	
	// AbstractMutator methods
	
	/**
	 * {@inheritDoc}
	 */
	
	@Override	
	protected void prepareMutation()
	{
		ISpecies spc = context.getSpecies();
		if (spc instanceof IMatrizSpecies) {
			// Sets individual species
			this.species = (IMatrizSpecies) spc;
			// Sets genotype schema
			this.schema = this.species.getGenotypeSchema();
		}
		else {
			throw new IllegalStateException("Invalid species in context");
		}
	}


	/**
	 * Flip method.
	 * 
	 * @param chrom Chromosome affected
	 * @param point1, point2, the point to change
	 */
	
	protected final void flip(int [] chrom, int point1, int point2)
	{		
		// New locus value
		int aux;;
		// Choose mutated value
		aux = chrom[point1];
		chrom[point1] = chrom[point2];
		chrom[point2] = aux;
	}	
	//metodo de mutacion adyacente
	protected final void flip2(int [][] chrom, int point)
	{		
		//Cogemos una operacion y la cambiamos de estacion por la misma de la que esta a uno de los lados
		for(int i=0; i<chrom[point].length; i++) {
			int aux = chrom[point][i];
			if(aux!=0) {
				for (int j=0; j<chrom[point+1].length; j++) {
					int aux2=chrom[point+1][j];
					if(aux2!=0) {
						chrom[point][i]=0;
						chrom[point][j]=aux;
					}
				}
			}
		}
	}	
}