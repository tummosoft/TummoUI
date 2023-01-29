export class accordion {
    public create(id: string) {
        var x = document.getElementById(id);
        x.querySelector(".l-accordion-toggle-open").removeAttribute("l-accordion-toggle-open");
    }
}