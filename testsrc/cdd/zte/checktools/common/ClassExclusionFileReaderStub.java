package cdd.zte.checktools.common;

public class ClassExclusionFileReaderStub extends ClassExclusionFileReader
{
    private String[] excludedPatterns = new String[0];

    @Override
    public String[] getExcludedClassPattern()
    {
        return excludedPatterns;
    }

    public void setExcludePattern(String... excludedPattern)
    {
        this.excludedPatterns = excludedPattern;
    }

    public void clearPattern()
    {
        excludedPatterns = new String[0];
    }
}
