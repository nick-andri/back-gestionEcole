package fr.dawan.miseEnSituation.dto;

import java.util.List;

public class EvaluationFrontPdfDto {

    private BlocCompetenceDto blocComp ;
    private List<CompetenceDto> competences;
    private double moyPromoByBloc;
    private double moyEtuByBloc;

    public BlocCompetenceDto getBlocComp() {
        return blocComp;
    }

    public void setBlocComp(BlocCompetenceDto blocComp) {
        this.blocComp = blocComp;
    }

    public List<CompetenceDto> getCompetences() {
        return competences;
    }

    public void setCompetences(List<CompetenceDto> competences) {
        this.competences = competences;
    }

    public double getMoyPromoByBloc() {
        return moyPromoByBloc;
    }

    public void setMoyPromoByBloc(double moyPromoByBloc) {
        this.moyPromoByBloc = moyPromoByBloc;
    }

    public double getMoyEtuByBloc() {
        return moyEtuByBloc;
    }

    public void setMoyEtuByBloc(double moyEtuByBloc) {
        this.moyEtuByBloc = moyEtuByBloc;
    }
}
