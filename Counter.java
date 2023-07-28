class Counter implements AutoCloseable{
    private int counter;
    private boolean isOpen;

    public Counter() {
        this.isOpen = true;
        this.counter = 0;
    }

    public Counter(int count){
        this.isOpen = true;
        this.counter = count;
    }

    public int add() throws CloseCounterException{
        if (!isOpen){
            throw new CloseCounterException("Счетчик закрыт");
        }
        return ++counter;
    }

    public void closeCounter(){
        this.isOpen = false;
    }

    @Override
    public void close(){
        closeCounter();
    }

    @Override
    public String toString() {
        return "Counter [counter=" + counter + ", isOpen=" + isOpen + "]";
    }
  
}

class CloseCounterException extends Exception{
    public CloseCounterException(String mess){
        super(mess);
    }
}
