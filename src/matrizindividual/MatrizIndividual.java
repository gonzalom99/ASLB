package matrizindividual;


import net.sf.jclec.IFitness;

import net.sf.jclec.IIndividual;
import net.sf.jclec.base.AbstractIndividual;

//import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Individual with a integer matrix as genotype.
 *
 * @author Gonzalo Martinez
 * @author Paula Robles
 * @author Maria Merino
 * 
 */

public class MatrizIndividual extends AbstractIndividual<int[][]>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3072035552876127168L;
	private static int[] duracion;
	/**
	 * Empty constructor
	 */

	public MatrizIndividual()
	{
		super();
	}
	/**
	 * Constructor that sets individual genotype
	 *
	 * @param genotype Individual genotype
	 */

	public MatrizIndividual(int[][] genotype)
	{
		super(genotype);
	}

	/**
	 * Constructor that sets individual genotype and fitness
	 *
	 * @param genotype Individual genotype
	 * @param fitness  Individual fitness
	 */

	public MatrizIndividual(int[][] genotype, IFitness fitness)
	{
		super(genotype, fitness);
	}
	
	/**
	 * Constructor that sets individual genotype and fitness
	 *
	 * @param genotype Individual genotype
	 * @param fitness  Individual fitness
	 */

	public MatrizIndividual(int[][] genotype, int[]duracion)
	{
		this.genotype=genotype; 
		this.setDuracion(duracion);
	}
	/**
	 * BinArrayIndividuals use 'Hamming distance' as distance.
	 *
	 * {@inheritDoc}
	 *
	 */
	
	@Override
	public double distance(IIndividual other) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IIndividual copy() {
		// TODO Auto-generated method stub
		return null;
	}
	public static int[] getDuracion() {
		return duracion;
	}
	public static void setDuracion(int[] duracion) {
		
	}

}
