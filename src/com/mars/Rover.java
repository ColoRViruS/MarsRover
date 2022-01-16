package com.mars;

public class Rover {
    
    protected int     mX;
    protected int     mY;
    protected Heading mHeading;
    protected String  mName;
    
    public Rover( int aX ,int aY ,Heading aHeading ) {
        mX = aX;
        mY = aY;
        mHeading = aHeading;
    }
    
    public Rover( String aName ,String aInit ) {
        String[] vStrings = aInit.split(" ");
        
        mX = Integer.parseInt(vStrings[0]);
        mY = Integer.parseInt(vStrings[1]);
        mHeading = Heading.valueOf(vStrings[2]);
        mName = aName;
    }
    
    public void TurnLeft( ) {
        mHeading = mHeading.TurnLeft();
    }
    
    public void TurnRight( ) {
        mHeading = mHeading.TurnReft();
    }
    
    public void Forward( ) {
        switch ( mHeading ) {
            case N:
                mY++;
                break;
            case E:
                mX++;
                break;
            case S:
                mY--;
                break;
            case W:
                mX--;
                break;
        }
    }
    
    @Override
    public String toString( ) {
        return mX + " " + mY + " " + mHeading;
    }
    
    public int getX( ) {
        return mX;
    }
    
    public void setX( int aX ) {
        mX = aX;
    }
    
    public int getY( ) {
        return mY;
    }
    
    public void setY( int aY ) {
        mY = aY;
    }
    
    public com.mars.Rover.Heading getHeading( ) {
        return mHeading;
    }
    
    public void setHeading( com.mars.Rover.Heading aHeading ) {
        mHeading = aHeading;
    }
    
    public String getName( ) {
        return mName;
    }
    
    public void setName( String aName ) {
        mName = aName;
    }
    
    public enum Heading {
        N,
        E,
        S,
        W;
        
        public Heading TurnLeft( ) {
            if ( ordinal() == 0 ) return W;
            return values()[ordinal() - 1];
        }
        
        public Heading TurnReft( ) {
            if ( ordinal() == 3 ) return N;
            return values()[ordinal() + 1];
        }
    }
}
