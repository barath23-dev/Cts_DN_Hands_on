import { ElementRef } from '@angular/core';
import { Highlight } from './highlight.directive';

describe('Highlight', () => {
  it('should create an instance', () => {
    const mockElementRef = new ElementRef(document.createElement('div'));
    const directive = new Highlight(mockElementRef);
    expect(directive).toBeTruthy();
  });
});
