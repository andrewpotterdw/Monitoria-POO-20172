package servidor;

import java.io.Serializable;

public class SimpleMessage implements Serializable
{
    private String simple;

    public SimpleMessage(String simple)
    {
        this.simple = simple;
    }

    public SimpleMessage() { }

    private String getSimple()
    {
        return this.simple;
    }

    public void setSimple(String simple)
    {
        this.simple = simple;
    }

    public String toString()
    {
        return this.simple;
    }

    public boolean equals(SimpleMessage simpleS) { return(simpleS.getSimple().equals(this.simple)); }
}
