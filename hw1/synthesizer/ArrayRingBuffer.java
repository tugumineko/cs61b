// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import org.junit.Test;
import synthesizer.AbstractBoundedQueue;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.capacity=capacity;
        rb =(T[]) new Object[this.capacity];
        first = 0;
        last = 0;
        fillcount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */

    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if(fillcount==capacity)throw new RuntimeException("Ring Buffer Overflow");
        rb[last] = x;
        last=(last+1)% capacity;
        fillcount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */

    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if(fillcount==0)throw new RuntimeException("Ring Buffer Underflow");
        T item = rb[first];
        first = (first+1)%capacity;
        fillcount--;
        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */

    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    private class ArrayRingBufferIterator implements Iterator<T> {
        private int ptr;
        private int curnum;

        public ArrayRingBufferIterator(){
            ptr = first;
            curnum = 0;
        }

        @Override
        public boolean hasNext() {
            return curnum<fillcount;
        }

        @Override
        public T next() {
            T temp = rb[ptr];
            ptr=(ptr+1)%capacity;
            curnum++;
            return temp;
        }

    }
    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }
}
