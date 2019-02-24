import java.io.Serializable;

/**
 * Class for packaging Line data for an IRAF file
 * @author nnova
 *
 */
class RAFLine implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -3313146258004709864L;
		/**
		 * Pointer location in bytes
		 */
		final long pointer;
		/**
		 * number of bytes for this line
		 */
		final int bytes;
		RAFLine(long pointer, int bytes){ this.pointer=pointer; this.bytes=bytes; }
		@Override
		public boolean equals(Object o){
			RAFLine other = (RAFLine) o;
			if(pointer == other.pointer && bytes==other.bytes)
				return true;
			return false;
		}
		@Override
		public String toString(){
			return " Pointer: " + pointer + " -- Bytes: " + bytes;
		}
	}