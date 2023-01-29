"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.accordion = void 0;
var accordion = /** @class */ (function () {
    function accordion() {
    }
    accordion.prototype.create = function (id) {
        var x = document.getElementById(id);
        x.querySelector(".l-accordion-toggle-open").removeAttribute("l-accordion-toggle-open");
    };
    return accordion;
}());
exports.accordion = accordion;
