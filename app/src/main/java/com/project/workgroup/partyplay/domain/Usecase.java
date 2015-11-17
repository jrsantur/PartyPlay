
package com.project.workgroup.partyplay.domain;


import rx.Observable;

public interface Usecase<T> {

    Observable<T> execute();
}
