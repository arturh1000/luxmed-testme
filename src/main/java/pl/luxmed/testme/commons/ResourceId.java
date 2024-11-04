package pl.luxmed.testme.commons;

import lombok.Builder;

@Builder //TODO : czy w recordach potrzebny jest builder ?
public record ResourceId(Long resourceId) implements LuxmedTestAppResponseBody {
    public boolean hasResourceId() {
        return resourceId > 0L;
    }
}
