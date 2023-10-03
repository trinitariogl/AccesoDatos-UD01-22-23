package org.example;

public class Cancion {
    private String cabecera;
    private String titulo;
    private String artista;
    private String album;
    private String año;
    private String comentario;
    private boolean tieneNumeroPista;
    private byte numeroPista;
    private GENERO genero;

    public enum GENERO {
        Blues,
        ClassicRock,
        Country,
        Dance,
        Disco,
        Funk,
        Grunge,
        HipHop,
        Jazz,
        Metal,
        NewAge,
        Oldies,
        Other,
        Pop,
        RnB,
        Rap,
        Reggae,
        Rock,
        Techno,
        Industrial,
        Alternative,
        Ska,
        DeathMetal,
        Pranks,
        Soundtrack,
        EuroTechno,
        Ambient,
        TripHop,
        Vocal,
        JazzFunk,
        Fusion,
        Trance,
        Classical,
        Instrumental,
        Acid,
        House,
        Game,
        SoundClip,
        Gospel,
        Noise,
        AlternativeRock,
        Bass,
        Soul,
        Punk,
        Space,
        Meditative,
        InstrumentalPop,
        InstrumentalRock,
        Ethnic,
        Gothic,
        Darkwave,
        TechnoIndustrial,
        Electronic,
        PopFolk,
        Eurodance,
        Dream,
        SouthernRock,
        Comedy,
        Cult,
        Gangsta,
        Top40,
        ChristianRap,
        PopFunk,
        Jungle,
        NativeAmerican,
        Cabaret,
        NewWave,
        Psychadelic,
        Rave,
        Showtunes,
        Trailer,
        LoFi,
        Tribal,
        AcidPunk,
        AcidJazz,
        Polka,
        Retro,
        Musical,
        RocknRoll,
        HardRock,
        Folk,
        FolkRock,
        NationalFolk,
        Swing,
        FastFusion,
        Bebob,
        Latin,
        Revival,
        Celtic,
        Bluegrass,
        Avantgarde,
        GothicRock,
        ProgressiveRock,
        PsychedelicRock,
        SymphonicRock,
        SlowRock,
        BigBand,
        Chorus,
        EasyListening,
        Acoustic,
        Humour,
        Speech,
        Chanson,
        Opera,
        ChamberMusic,
        Sonata,
        Symphony,
        BootyBass,
        Primus,
        PornGroove,
        Satire,
        SlowJam,
        Club,
        Tango,
        Samba,
        Folklore,
        Ballad,
        PowerBallad,
        RhythmicSoul,
        Freestyle,
        Duet,
        PunkRock,
        DrumSolo,
        Acapella,
        EuroHouse,
        DanceHall,
        Goa,
        DrumAndBass,
        ClubHouse,
        Hardcore,
        Terror,
        Indie,
        BritPop,
        Negerpunk,
        PolskPunk,
        Beat,
        ChristianGangstaRap,
        HeavyMetal,
        BlackMetal,
        Crossover,
        ContemporaryChristian,
        ChristianRock,
        Merengue,
        Salsa,
        ThrashMetal,
        Anime,
        JPop,
        Synthpop;
    }


    public Cancion() {
    }

    public Cancion(String cabecera, String titulo, String artista, String album, String año, String comentario, boolean tieneNumeroPista, byte numeroPista, GENERO genero) {
        this.cabecera = arreglarVacios(cabecera);
        this.titulo = arreglarVacios(titulo);
        this.artista = arreglarVacios(artista);
        this.album = arreglarVacios(album);
        this.año = arreglarVacios(año);
        this.comentario = arreglarVacios(comentario);
        this.tieneNumeroPista = tieneNumeroPista;
        this.numeroPista = numeroPista;
        this.genero = genero;
    }

    public String getCabecera() {
        return cabecera;
    }

    public void setCabecera(String cabecera) {
        this.cabecera = cabecera;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isTieneNumeroPista() {
        return tieneNumeroPista;
    }

    public void setTieneNumeroPista(boolean tieneNumeroPista) {
        this.tieneNumeroPista = tieneNumeroPista;
    }

    public byte getNumeroPista() {
        return numeroPista;
    }

    public void setNumeroPista(byte numeroPista) {
        this.numeroPista = numeroPista;
    }

    public GENERO getGenero() {
        return genero;
    }

    public void setGenero(GENERO genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "cabecera='" + cabecera + '\'' +
                ", titulo='" + titulo + '\'' +
                ", artista='" + artista + '\'' +
                ", album='" + album + '\'' +
                ", año='" + año + '\'' +
                ", comentario='" + comentario + '\'' +
                ", tieneNumeroPista=" + tieneNumeroPista +
                ", numeroPista=" + numeroPista +
                ", genero=" + genero +
                '}';
    }

    public String arreglarVacios(String original) {
        StringBuilder nuevo = new StringBuilder();
        for (int i = 0; i < original.length(); i++) {
            char caracterActual = original.charAt(i);
            if (caracterValido(caracterActual)) {
                nuevo.append(caracterActual);
            }
        }

        return nuevo.toString();
    }

    private boolean caracterValido(char caracterActual) {
        if ((caracterActual >= 'a' && caracterActual <= 'z') || (caracterActual >= 'A' && caracterActual <= 'Z')
                || caracterActual == '-' || caracterActual == ' ' || (caracterActual >= '0' && caracterActual <= '9')){
            return true;
        } else {
            return false;
        }

    }

    public void mostrarDatos() {
        System.out.printf("Título: %s", titulo + "\n");
        System.out.printf("Artísta: %s", artista + "\n");
        System.out.printf("Album: %s", album + "\n");
        System.out.printf("Pista Número: %s", numeroPista + "\n");
        System.out.printf("Año: %s", año + "\n");
        System.out.printf("Comentario: %s", comentario + "\n");
        System.out.printf("Género: %s", genero + "\n");
    }
}

