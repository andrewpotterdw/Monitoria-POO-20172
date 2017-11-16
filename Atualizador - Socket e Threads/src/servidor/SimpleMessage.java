package servidor;

import java.io.Serializable;

public class SimpleMessage implements Serializable
{
    String simple;

    public SimpleMessage(String simple)
    {
        this.simple = simple;
    }

    public SimpleMessage()
    {

    }

    public String getSimple()
    {
        return this.simple;
    }

    public void setSimple(String simple)
    {
        this.simple = simple;
    }

    public String toString()
    {
        return "MSG: " + this.simple;
    }

    public boolean equals(SimpleMessage simpleS)
    {
        if(simpleS.getSimple().equals(this.simple))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
