
package edu.umb.cs681.hw01;

@FunctionalInterface
interface Observer {
    void update(Observable obs, Object obj);
}
